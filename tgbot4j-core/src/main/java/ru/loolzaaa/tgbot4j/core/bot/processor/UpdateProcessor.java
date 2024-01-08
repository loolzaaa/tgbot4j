package ru.loolzaaa.tgbot4j.core.bot.processor;

import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.bot.TelegramBot;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

/**
 * This interface describes processing fresh updates.
 * <p>
 * Each processor must perform one functional role.
 * <p>
 * Processors are registered in a specific order
 * in the processor chain using a {@link TelegramBot#registerUpdateProcessor(UpdateProcessor, int)}.
 *
 * @see UpdateProcessorChain
 */

public interface UpdateProcessor {
    /**
     * Processing a fresh update.
     * <p>
     * After completion of processing {@link Update} and completion
     * of all necessary interactions with the API server
     * using the {@link MethodSender}, each processor
     * must either transfer the update processing
     * to the next processor in the chain,
     * or interrupt the processing chain.
     *
     * <pre>
     *     {@code
     *     @Override
     *     public void process(Update update, MethodSender methodSender, UpdateProcessorChain chain) {
     *         try {
     *             if (some condition) {
     *                 return; // interrupt processing
     *             }
     *             // do some update processing
     *             chain.doProcess(update, methodSender); // transfer processing to next processing
     *         } finally {
     *             // do some stuff AFTER ALL processors
     *         }
     *     }
     *     }
     * </pre>
     *
     * @param update       fresh update from API server
     * @param methodSender sender for API server communication
     * @param chain        update processors chain
     */
    void process(Update update, MethodSender methodSender, UpdateProcessorChain chain);
}
