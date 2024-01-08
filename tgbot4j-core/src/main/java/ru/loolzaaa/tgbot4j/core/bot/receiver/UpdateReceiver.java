package ru.loolzaaa.tgbot4j.core.bot.receiver;

import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.bot.TelegramBot;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * This interface defines the method (long polling,
 * webhook, etc.) of receiving updates from the API server.
 */

public interface UpdateReceiver {
    /**
     * Starting the process of receiving updates
     * from the API server.
     * <p>
     * The receiver implementation should fill the queue
     * with fresh updates.
     * <p>
     * Any interaction with the message queue
     * must be synchronized.
     * <p>
     * This method must be called during the {@link TelegramBot#init()}.
     *
     * @param updates queue for fresh updates
     */
    void start(ConcurrentLinkedDeque<Update> updates);

    /**
     * Stopping all components of the receiver,
     * freeing up resources.
     * <p>
     * This method must be called during the {@link TelegramBot#destroy()}.
     */
    void stop();

    /**
     * Method for determining whether the receiver
     * is running or not.
     *
     * @return true if receiver is running,
     * false otherwise
     */
    boolean isRunning();
}
