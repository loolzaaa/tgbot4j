package ru.loolzaaa.tgbot4j.core.bot;

import ru.loolzaaa.tgbot4j.core.bot.processor.UpdateProcessor;
import ru.loolzaaa.tgbot4j.core.bot.processor.UpdateProcessorChain;
import ru.loolzaaa.tgbot4j.core.bot.receiver.UpdateReceiver;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

/**
 * The main interface describing the Telegram Bot.
 * <p>
 * Each bot must contain:
 * <ul>
 *     <li>Some queue for storing received updates.</li>
 *     <li>{@link UpdateReceiver} instance for receiving
 *     fresh updates</li>
 *     <li>Some process for handling
 *     and saving fresh updates</li>
 *     <li>{@link UpdateProcessorChain} instance
 *     for process received updates</li>
 *     <li>{@link MethodSender} instance
 *     for API server communication</li>
 * </ul>
 */

public interface TelegramBot {
    /**
     * Registering an {@link UpdateProcessor} for a bot.
     * <p>
     * Each processor must have a unique order number.
     * <p>
     * The smaller order number, the earlier the processor
     * will be launched.
     *
     * @param updateProcessor registered update processor
     * @param order           processor order number
     */
    void registerUpdateProcessor(UpdateProcessor updateProcessor, int order);

    /**
     * Initialization of Telegram Bot.
     * <p>
     * While executing this method, it is necessary
     * to create and launch all components of the bot
     * (threads for receiving and processing updates,
     * servers for webhooks, etc.).
     */
    void init();

    /**
     * Destruction of Telegram Bot.
     * <p>
     * Executing this method implies stopping all threads
     * and servers running during initialization,
     * as well as releasing all necessary resources.
     */
    void destroy();
}
