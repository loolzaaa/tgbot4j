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

public final class ServerlessWebhookUpdateReceiver implements UpdateReceiver {

    private static final Logger log = LoggerFactory.getLogger(ServerlessWebhookUpdateReceiver.class);

    private final String botName;
    private final String botToken;

    private final SetWebhook setWebhook;

    private final UpdateHandler updateHandler;

    private final ReceiverOptions options;

    private volatile boolean isRunning = false;

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

    @Override
    public void start(ConcurrentLinkedDeque<Update> updates) {
        if (isRunning) {
            throw new IllegalStateException(botName + " receiver already running!");
        }

        if (setWebhook != null) {
            boolean setWebhookResult = WebhookUtils.setWebhook(botToken, setWebhook);
            if (setWebhookResult) {
                log.info("Webhook for {} successfully set", botName);
            } else {
                log.error("Cannot set webhook for {}", botName);
                throw new IllegalStateException("Cannot set webhook for " + botName);
            }
        } else {
            WebhookInfo webhookInfo = WebhookUtils.getWebhook(botToken);
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

    @Override
    public void stop() {
        if (!isRunning) {
            log.info(botName + " receiver not started or already stopped");
            return;
        }

        isRunning = false;
        log.info("{} webhook receiver stopped", botName);
    }

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

    @Getter
    @Setter
    @ToString
    public static class ReceiverOptions {
        private String botPath = "/bot";
        private String secretToken = null;
    }

    public static final class UpdateHandler {

        @Setter
        private UpdateHandlerInternal target;

        public void handle(String data, String secretToken) {
            try {
                target.handle(data, secretToken);
            } catch (IOException e) {
                throw new ApiRequestException(e.getMessage());
            }
        }
    }

    @RequiredArgsConstructor
    private class UpdateHandlerInternal {

        private final ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        private final ConcurrentLinkedDeque<Update> receivedUpdates;

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
