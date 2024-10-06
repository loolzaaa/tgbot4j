package ru.loolzaaa.tgbot4j.bot.receiver;


import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.loolzaaa.tgbot4j.bot.sender.DefaultMethodSender;
import ru.loolzaaa.tgbot4j.core.api.methods.GetUpdates;
import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.api.types.WebhookInfo;
import ru.loolzaaa.tgbot4j.core.bot.receiver.UpdateReceiver;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;
import ru.loolzaaa.tgbot4j.util.WebhookUtils;
import ru.loolzaaa.tgbot4j.util.backoff.BackOff;
import ru.loolzaaa.tgbot4j.util.backoff.ExponentialBackOff;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Long polling implementation of update receiver.
 * <p>
 * Allows set custom update supplier
 * and customize receiver options.
 */

public final class LongPollingUpdateReceiver implements UpdateReceiver {

    private static final Logger log = LoggerFactory.getLogger(LongPollingUpdateReceiver.class);

    private final String botName;
    private final String botToken;

    private final Supplier<List<Update>> updatesSupplier;

    private final ReceiverOptions options;

    private volatile boolean isRunning = false;

    private ScheduledExecutorService receiverService;

    private int lastReceivedUpdateId;

    /**
     * Constructor creates new long polling update receiver.
     * <p>
     * If update supplier and/or receiver option is null,
     * creates default.
     *
     * @param botName         bot name
     * @param botToken        bot token
     * @param updatesSupplier custom update supplier
     * @param options         update receiver options
     */
    public LongPollingUpdateReceiver(@NonNull String botName,
                                     @NonNull String botToken,
                                     Supplier<List<Update>> updatesSupplier,
                                     ReceiverOptions options) {
        this.botName = botName;
        this.botToken = botToken;
        this.options = Objects.requireNonNullElseGet(options, ReceiverOptions::new);
        this.updatesSupplier = Objects.requireNonNullElseGet(updatesSupplier, DefaultUpdateSupplier::new);
    }

    /**
     * Start receiving updates with long polling.
     * <p>
     * There is no way to run two or more instances
     * of same update receiver.
     * <p>
     * If there is webhook for bot, delete it
     * if clearWebhookIfExist option is set,
     * else throw exception.
     * <p>
     * Receive updates in separate single thread executor
     * with predefined customizable delay.
     *
     * @param updates queue for fresh updates
     */
    @Override
    public void start(ConcurrentLinkedDeque<Update> updates) {
        if (updates == null){
            throw new IllegalArgumentException("Updates deque must not be null");
        }

        if (isRunning) {
            throw new IllegalStateException(botName + " receiver already running!");
        }

        WebhookInfo webhookInfo = WebhookUtils.getWebhook(botToken, null);
        if (options.clearWebhookIfExist) {
            if (webhookInfo.getUrl() != null && !webhookInfo.getUrl().isEmpty()) {
                boolean deleteWebhookResult = WebhookUtils.deleteWebhook(botToken, false, null);
                log.info("Webhook for {} delete result: {}", botName, deleteWebhookResult);
            }
        } else {
            if (webhookInfo.getUrl() != null && !webhookInfo.getUrl().isEmpty()) {
                throw new IllegalStateException("You need to delete webhook before start long polling receiver");
            }
        }

        receiverService = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "Receiver-" + botName);
            thread.setPriority(Thread.MIN_PRIORITY);
            return thread;
        });

        lastReceivedUpdateId = 0;

        ReceiverTask receiverTask = new ReceiverTask(updates, updatesSupplier);
        receiverService.scheduleWithFixedDelay(receiverTask, 100, options.receiverTaskDelay, TimeUnit.MILLISECONDS);

        isRunning = true;
        log.info("{} long polling receiver started with next options: {}", botName, options);
    }

    /**
     * Stop receiving updates with long polling.
     * <p>
     * If already stops, do nothing.
     * <p>
     * Shutdown executor and set run flag to false.
     */
    @Override
    public void stop() {
        if (!isRunning) {
            log.info("{} receiver not started or already stopped", botName);
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

    /**
     * Get current run status of update receiver.
     *
     * @return current run status
     */
    @Override
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Long polling update receiver options.
     *
     * <ul>
     *     <li>clearWebhookIfExist - need to remove existing webhook
     *     while start receiver</li>
     *     <li>receiverTaskDelay - fixed delay for receiving updates</li>
     *     <li>connectTimeout - http client timeout</li>
     *     <li>requestTimeout - http request timeout</li>
     *     <li>maxThreads - update supplier thread pool size</li>
     *     <li>updateTimeout - timeout in seconds for long polling</li>
     *     <li>updateLimit - limits the number of updates to be retrieved</li>
     *     <li>updateAllowedUpdates - allowed update types</li>
     * </ul>
     *
     * @see GetUpdates
     */
    @Getter
    @Setter
    @ToString
    public static class ReceiverOptions {
        private boolean clearWebhookIfExist = false;
        private int receiverTaskDelay = 500;
        private int connectTimeout = 75 * 1000;
        private int requestTimeout = 100 * 1000;
        private int maxThreads = 1;
        private int updateTimeout = 50;
        private int updateLimit = 100;
        private List<String> updateAllowedUpdates;
    }

    /**
     * Separate task for update receiving.
     * <p>
     * Received updates and notify Telegram bot to handle its.
     * <p>
     * Watch for last received update is to skip repetitions.
     */
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

    /**
     * Default implementation for update supplier.
     * <p>
     * Just send {@link GetUpdates} with predefined options.
     */
    private class DefaultUpdateSupplier implements Supplier<List<Update>> {

        private final MethodSender methodSender;

        private final BackOff backOff = new ExponentialBackOff();

        /**
         * Constructor creates new default update supplier.
         * <p>
         * Setting options for default method sender.
         */
        public DefaultUpdateSupplier() {
            if (options.requestTimeout <= (options.updateTimeout * 1000)) {
                log.warn("Timeout for http request ({}) usually greater than GetUpdates method timeout ({}).",
                        options.requestTimeout, options.updateTimeout);
            }
            DefaultMethodSender.SenderOptions senderOptions = new DefaultMethodSender.SenderOptions();
            senderOptions.setConnectTimeout(options.getConnectTimeout());
            senderOptions.setRequestTimeout(options.getRequestTimeout());
            senderOptions.setMaxThreads(options.getMaxThreads());
            this.methodSender = new DefaultMethodSender(botToken, senderOptions);
        }

        /**
         * Send {@link GetUpdates} with predefined parameters
         * for receiving updates.
         *
         * @return new updates
         */
        @Override
        public List<Update> get() {
            GetUpdates getUpdates = new GetUpdates();
            getUpdates.setLimit(options.updateLimit);
            getUpdates.setTimeout(options.updateTimeout);
            getUpdates.setOffset(lastReceivedUpdateId + 1);
            if (options.updateAllowedUpdates != null) {
                getUpdates.setAllowedUpdates(options.updateAllowedUpdates);
            }
            try {
                List<Update> updates = methodSender.send(getUpdates);
                backOff.reset();
                return updates;
            } catch (Exception e) {
                long waitInterval = backOff.nextBackOff();
                try {
                    Thread.sleep(waitInterval);
                } catch (InterruptedException ex) {
                    log.warn("GetUpdates got interrupted while sleeping in backoff mode.", ex);
                }
                return Collections.emptyList();
            }
        }
    }
}
