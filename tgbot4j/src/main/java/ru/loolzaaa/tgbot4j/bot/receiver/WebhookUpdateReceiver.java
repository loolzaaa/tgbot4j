package ru.loolzaaa.tgbot4j.bot.receiver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.loolzaaa.tgbot4j.core.api.methods.SetWebhook;
import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.api.types.WebhookInfo;
import ru.loolzaaa.tgbot4j.core.bot.receiver.UpdateReceiver;
import ru.loolzaaa.tgbot4j.util.WebhookUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executor;

import static ru.loolzaaa.tgbot4j.core.Constants.*;

public final class WebhookUpdateReceiver implements UpdateReceiver {

    private static final Logger log = LoggerFactory.getLogger(WebhookUpdateReceiver.class);

    private final String botName;
    private final String botToken;

    private final SetWebhook setWebhook;

    private final ReceiverOptions options;

    private volatile boolean isRunning = false;

    private HttpServer server;

    public WebhookUpdateReceiver(@NonNull String botName,
                                 @NonNull String botToken,
                                 SetWebhook setWebhook,
                                 ReceiverOptions options) {
        this.botName = botName;
        this.botToken = botToken;
        this.setWebhook = setWebhook;
        this.options = Objects.requireNonNullElseGet(options, ReceiverOptions::new);
        if (this.options.server != null) {
            this.options.serverPort = -1;
        }
        sanitizeContextPath();
    }

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

        UpdateHandler updateHandler = new UpdateHandler(updates);
        if (options.server == null) {
            if (!webhookSupportedPorts.contains(options.serverPort)) {
                log.warn("Ports currently supported for webhooks: {}. Current port: {}", webhookSupportedPorts, options.serverPort);
            }
            try {
                server = HttpServer.create(new InetSocketAddress(options.serverPort), 0);
                server.createContext(options.botPath, updateHandler);
                server.setExecutor(options.executor);
                server.start();
            } catch (Exception e) {
                log.error(e.getLocalizedMessage(), e);
                throw new RuntimeException(e);
            }
        } else {
            server = options.server;
            server.createContext(options.botPath, updateHandler);
            log.info("Using predefined server: {}. Don't forget to start it if needed!", server);
        }

        isRunning = true;
        log.info("{} webhook receiver started with next options: {}", botName, options);
    }

    @Override
    public void stop() {
        if (!isRunning) {
            log.info(botName + " receiver not started or already stopped");
            return;
        }

        if (options.server == null) {
            server.stop(2);
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
        private HttpServer server = null;
        private int serverPort = 8080;
        private String botPath = "/bot";
        private Executor executor = null;
        private String secretToken = null;
    }

    @RequiredArgsConstructor
    private class UpdateHandler implements HttpHandler {

        private final ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        private final ConcurrentLinkedDeque<Update> receivedUpdates;

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            int responseCode = HTTP_CODE_OK;
            if (exchange.getRequestMethod().equals(HTTP_METHOD_POST)) {
                Headers requestHeaders = exchange.getRequestHeaders();
                String secretTokenHeader = requestHeaders.getFirst(SECRET_TOKEN_HEADER);
                if (options.secretToken != null && !options.secretToken.equals(secretTokenHeader)) {
                    responseCode = HTTP_CODE_UNAUTHORIZED;
                } else {
                    Update update = mapper.readValue(exchange.getRequestBody(), Update.class);
                    if (log.isTraceEnabled()) {
                        log.trace("Received update: {}", update);
                    }
                    synchronized (receivedUpdates) {
                        receivedUpdates.add(update);
                        receivedUpdates.notifyAll();
                    }
                }
            } else {
                responseCode = HTTP_CODE_METHOD_NOT_ALLOWED;
                /*
                 Closing an exchange without consuming all the request body
                 is not an error but may make the underlying TCP connection
                 unusable for following exchanges.
                */
                exchange.getRequestBody().readAllBytes();
            }
            exchange.getResponseHeaders().set(CHARSET_HEADER, StandardCharsets.UTF_8.name());
            exchange.getResponseHeaders().set(CONTENT_TYPE_HEADER, TEXT_CONTENT_TYPE_VALUE);
            exchange.sendResponseHeaders(responseCode, -1);
            exchange.close();
        }
    }
}
