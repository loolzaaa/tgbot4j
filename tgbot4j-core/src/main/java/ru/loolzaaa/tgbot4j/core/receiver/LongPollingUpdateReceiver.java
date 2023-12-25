package ru.loolzaaa.tgbot4j.core.receiver;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.loolzaaa.tgbot4j.core.api.methods.GetUpdates;
import ru.loolzaaa.tgbot4j.core.api.types.Update;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static ru.loolzaaa.tgbot4j.core.Constants.*;

public class LongPollingUpdateReceiver implements UpdateReceiver {

    private static final Logger log = LoggerFactory.getLogger(LongPollingUpdateReceiver.class);

    private final String botName;
    private final String botToken;

    private final Supplier<List<Update>> updatesSupplier;

    private final ReceiverOptions options;

    private volatile boolean isRunning = false;

    private ScheduledExecutorService receiverService;

    private int lastReceivedUpdateId;

    public LongPollingUpdateReceiver(@NonNull String botName,
                                     @NonNull String botToken,
                                     Supplier<List<Update>> updatesSupplier,
                                     ReceiverOptions options) {
        this.botName = botName;
        this.botToken = botToken;
        this.updatesSupplier = updatesSupplier;
        this.options = Objects.requireNonNullElseGet(options, ReceiverOptions::new);
    }

    @Override
    public void start(ConcurrentLinkedDeque<Update> updates) {
        if (isRunning) {
            throw new IllegalStateException(botName + " receiver already running!");
        }

        //TODO: clear webhook

        receiverService = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "Receiver-" + botName);
            thread.setPriority(Thread.MIN_PRIORITY);
            return thread;
        });

        lastReceivedUpdateId = 0;

        ReceiverTask receiverTask = new ReceiverTask(updates, Objects.requireNonNullElseGet(updatesSupplier, DefaultUpdateSupplier::new));
        receiverService.scheduleWithFixedDelay(receiverTask, 100, options.receiverTaskDelay, TimeUnit.MILLISECONDS);

        isRunning = true;
        log.info("{} long polling receiver started with next options: {}", botName, options);
    }

    @Override
    public void stop() {
        if (!isRunning) {
            log.info(botName + " receiver not started or already stopped");
            return;
        }

        receiverService.shutdown();
        try {
            if (!receiverService.awaitTermination(2000, TimeUnit.MILLISECONDS)) {
                receiverService.shutdownNow();
            }
        } catch (InterruptedException e) {
            receiverService.shutdownNow();
            Thread.currentThread().interrupt();
        }
        isRunning = false;
        log.info("{} long polling receiver stopped", botName);
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Getter
    @Setter
    @ToString
    public static class ReceiverOptions {
        private int receiverTaskDelay = 500;
        private int connectTimeout = 30 * 1000;
        private int requestTimeout = 30 * 1000;
        private int updateTimeout = 50;
        private int updateLimit = 100;
        private List<String> updateAllowedUpdates;
    }

    @RequiredArgsConstructor
    private class ReceiverTask implements Runnable {

        private final ConcurrentLinkedDeque<Update> receivedUpdates;

        private final Supplier<List<Update>> updatesSupplier;

        @Override
        public void run() {
            try {
                List<Update> updates = updatesSupplier.get();
                if (updates.isEmpty()) {
                    return;
                }
                if (log.isTraceEnabled()) {
                    log.trace("Received {} updates: {}", updates.size(), updates);
                } else if (log.isDebugEnabled()) {
                    log.debug("Received {} updates", updates.size());
                }
                updates.removeIf(update -> update.getUpdateId() < lastReceivedUpdateId);
                lastReceivedUpdateId = updates.parallelStream()
                        .map(Update::getUpdateId)
                        .max(Integer::compareTo)
                        .orElse(0);
                log.debug("Current last received update id: {}", lastReceivedUpdateId);
                synchronized (receivedUpdates) {
                    receivedUpdates.addAll(updates);
                    receivedUpdates.notifyAll();
                }
            } catch (Exception e) {
                log.error(e.getLocalizedMessage(), e);
            }
        }
    }

    private class DefaultUpdateSupplier implements Supplier<List<Update>> {

        private final HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.of(options.connectTimeout, ChronoUnit.MILLIS))
                .build();

        private final ObjectMapper mapper = new ObjectMapper();

        @Override
        public List<Update> get() {
            GetUpdates getUpdates = new GetUpdates();
            getUpdates.setLimit(options.updateLimit);
            getUpdates.setTimeout(options.updateTimeout);
            getUpdates.setOffset(lastReceivedUpdateId + 1);
            if (options.updateAllowedUpdates != null) {
                getUpdates.setAllowedUpdates(options.updateAllowedUpdates);
            }

            //TODO: Extract it? Inject sender for this purposes?
            try {
                final String url = BASE_URL + botToken + "/" + GetUpdates.class.getSimpleName();
                final String body = mapper.writeValueAsString(getUpdates);
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .timeout(Duration.of(options.requestTimeout, ChronoUnit.MILLIS))
                        .POST(BodyPublishers.ofString(body))
                        .uri(URI.create(url))
                        .header(CHARSET_HEADER, StandardCharsets.UTF_8.name())
                        .header(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE_VALUE)
                        .build();

                HttpResponse<String> response = httpClient.send(httpRequest, BodyHandlers.ofString(StandardCharsets.UTF_8));
                int statusCode = response.statusCode();
                if (statusCode == 200) {
                    return getUpdates.deserializeResponse(mapper, response.body());
                }
                log.warn("{} error status code: {}. Response content: {}",
                        GetUpdates.class.getSimpleName(), statusCode, response.body());
            } catch (InterruptedException e) {
                log.info("{} request interrupted with message: {}", GetUpdates.class.getSimpleName(), e.getLocalizedMessage());
            } catch (IOException e) {
                log.error(e.getLocalizedMessage(), e);
            }
            return Collections.emptyList();
        }
    }
}
