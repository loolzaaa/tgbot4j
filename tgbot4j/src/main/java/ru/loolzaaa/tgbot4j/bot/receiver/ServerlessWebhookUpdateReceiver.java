package ru.loolzaaa.tgbot4j.bot.receiver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.loolzaaa.tgbot4j.core.api.methods.SetWebhook;
import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.api.types.WebhookInfo;
import ru.loolzaaa.tgbot4j.core.bot.receiver.UpdateReceiver;
import ru.loolzaaa.tgbot4j.core.exception.ApiRequestException;
import ru.loolzaaa.tgbot4j.util.WebhookUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Serverless implementation of update receiver.
 * <p>
 * Allows set custom set webhook API object,
 * and customize receiver options.
 * <p>
 * All updates handles by predefined {@link UpdateHandler}.
 */

public final class ServerlessWebhookUpdateReceiver implements UpdateReceiver {

    private static final Logger log = LoggerFactory.getLogger(ServerlessWebhookUpdateReceiver.class);

    private final String botName;
    private final String botToken;

    private final SetWebhook setWebhook;

    private final UpdateHandler updateHandler;

    private final ReceiverOptions options;

    private volatile boolean isRunning = false;

    /**
     * Constructor creates new serverless update receiver.
     * <p>
     * if receiver options is null, creates default.
     * <p>
     * Also, prepare some sanitizing for context path.
     *
     * @param botName       bot name
     * @param botToken      bot token
     * @param setWebhook    set webhook API object
     * @param updateHandler predefined update handler
     * @param options       update receiver options
     */
    public ServerlessWebhookUpdateReceiver(@NonNull String botName,
                                           @NonNull String botToken,
                                           SetWebhook setWebhook,
                                           @NonNull UpdateHandler updateHandler,
                                           ReceiverOptions options) {
        this.botName = botName;
        this.botToken = botToken;
        this.setWebhook = setWebhook;
        this.updateHandler = updateHandler;
        this.options = Objects.requireNonNullElseGet(options, ReceiverOptions::new);
        sanitizeContextPath();
    }

    /**
     * Start receiving updates with serverless.
     * <p>
     * There is no way to run two or more instances
     * of same update receiver.
     * <p>
     * If set webhook defined for bot, send it and check result.
     * if set webhook not defined, check already existing webhook,
     * else throw exception if there is not existing webhook.
     * <p>
     * Receive updates in internal handler,
     * that set as target for custom update handler
     *
     * @param updates queue for fresh updates
     */
    @Override
    public void start(ConcurrentLinkedDeque<Update> updates) {
        if (isRunning) {
            throw new IllegalStateException(botName + " receiver already running!");
        }

        if (setWebhook != null) {
            boolean setWebhookResult = WebhookUtils.setWebhook(botToken, setWebhook, null);
            if (setWebhookResult) {
                log.info("Webhook for {} successfully set", botName);
            } else {
                log.error("Cannot set webhook for {}", botName);
                throw new IllegalStateException("Cannot set webhook for " + botName);
            }
        } else {
            WebhookInfo webhookInfo = WebhookUtils.getWebhook(botToken, null);
            if (webhookInfo.getUrl() == null || webhookInfo.getUrl().isEmpty()) {
                throw new IllegalStateException("You need to set webhook before start webhook receiver");
            } else {
                log.info("Current {} webhook: {}", botName, webhookInfo);
            }
        }

        UpdateHandlerInternal updateHandlerInternal = new UpdateHandlerInternal(updates);
        updateHandler.setTarget(updateHandlerInternal);

        isRunning = true;
        log.info("{} webhook receiver started with next options: {}", botName, options);
    }

    /**
     * Stop receiving updates with webhook.
     * <p>
     * If already stops, do nothing.
     */
    @Override
    public void stop() {
        if (!isRunning) {
            log.info("{} receiver not started or already stopped", botName);
            return;
        }

        isRunning = false;
        log.info("{} webhook receiver stopped", botName);
    }

    /**
     * Get current run status of update receiver.
     *
     * @return current run status
     */
    @Override
    public boolean isRunning() {
        return isRunning;
    }

    private void sanitizeContextPath() {
        String contextPath = "/";
        if (options.botPath != null) {
            if (options.botPath.startsWith("/")) {
                contextPath += options.botPath.substring(1);
            } else {
                contextPath += options.botPath;
            }
        }
        if (!contextPath.endsWith("/")) {
            contextPath += "/";
        }
        options.botPath = contextPath;
    }

    /**
     * Serverless update receiver options.
     *
     * <ul>
     *     <li>botPath - bot mapping context path for incoming request</li>
     *     <li>secretToken - token for incoming webhook request</li>
     * </ul>
     */
    @Getter
    @Setter
    @ToString
    public static class ReceiverOptions {
        private String botPath = "/bot";
        private String secretToken = null;
    }

    /**
     * Predefined update handler.
     * <p>
     * Developer must create instance of it
     * and provide for this update receiver.
     * <p>
     * All incoming requests must invoke {@link #handle(String, String)}
     * method of this handler.
     */
    public static final class UpdateHandler {

        @Setter
        private UpdateHandlerInternal target;

        /**
         * Serverless incoming request handler.
         * <p>
         * Must be invoked from user application.
         *
         * @param data        incoming data
         * @param secretToken incoming request secret token
         */
        public void handle(String data, String secretToken) {
            try {
                target.handle(data, secretToken);
            } catch (IOException e) {
                throw new ApiRequestException(e.getMessage());
            }
        }
    }

    /**
     * Internal update handler.
     * <p>
     * Invoked by user defined {@link UpdateHandler}.
     */
    @RequiredArgsConstructor
    private class UpdateHandlerInternal {

        private final ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        private final ConcurrentLinkedDeque<Update> receivedUpdates;

        /**
         * Handler incoming request with new update.
         * <p>
         * Check secret token header if needed,
         * deserialize request and notify bot to handle its.
         *
         * @param data        incoming data
         * @param secretToken incoming request secret token
         * @throws IOException if request/response error
         */
        public void handle(String data, String secretToken) throws IOException {
            if (options.secretToken != null && !options.secretToken.equals(secretToken)) {
                throw new ApiRequestException("Invalid secret token");
            } else {
                Update update = mapper.readValue(data, Update.class);
                if (log.isTraceEnabled()) {
                    log.trace("Received update: {}", update);
                }
                synchronized (receivedUpdates) {
                    receivedUpdates.add(update);
                    receivedUpdates.notifyAll();
                }
            }
        }
    }
}
