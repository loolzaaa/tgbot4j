package ru.loolzaaa.tgbot4j.core.bot.processor;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.bot.TelegramBot;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class is a chain for launching update processors.
 * <p>
 * This chain is created using a {@link Builder},
 * into which update processors are added.
 * <p>
 * Once an instance is created, the chain cannot be changed.
 */

@RequiredArgsConstructor
public final class UpdateProcessorChain {

    private static final Logger log = LoggerFactory.getLogger(UpdateProcessorChain.class);

    private final List<UpdateProcessor> updateProcessors;

    private int currentProcessorPosition = 0;

    /**
     * Transferring update processing to the next
     * update processor in chain.
     * <p>
     * Processors are started in the order indicated
     * when they are added to the chain.
     * <p>
     * For any unhandled exception, the processing process
     * ends and will begin again for the next update.
     *
     * @param update       processed update
     * @param methodSender sender for API server communication
     */
    public void doProcess(Update update, MethodSender methodSender) {
        try {
            doProcessInternal(update, methodSender);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        } finally {
            currentProcessorPosition = 0;
        }
    }

    private void doProcessInternal(Update update, MethodSender methodSender) {
        if (currentProcessorPosition == updateProcessors.size()) {
            return;
        }
        UpdateProcessor nextProcessor = updateProcessors.get(currentProcessorPosition++);
        if (log.isTraceEnabled()) {
            String name = nextProcessor.getClass().getSimpleName();
            log.trace("Invoking {} ({}/{})", name, currentProcessorPosition, updateProcessors.size());
        }
        nextProcessor.process(update, methodSender, this);
    }

    /**
     * Builder for construct {@link UpdateProcessorChain}.
     * <p>
     * Each {@link TelegramBot} implementation
     * must contain this builder and use it
     * when register update processor.
     */
    public static class Builder {

        private final List<OrderedUpdateProcessor> orderedUpdateProcessors = new ArrayList<>(4);

        /**
         * Preliminary addition of a processor update
         * to the internal list.
         * <p>
         * At this stage, the order is checked
         * but not taken into account.
         *
         * @param updateProcessor registered update processor
         * @param order           processor order number
         */
        public void addUpdateProcessor(UpdateProcessor updateProcessor, int order) {
            orderedUpdateProcessors.add(new OrderedUpdateProcessor(updateProcessor, order));
            log.info("Add new update processor {} with order {}", updateProcessor, order);
        }

        /**
         * Creation of {@link UpdateProcessorChain} instance.
         * <p>
         * Before creating a chain of processors,
         * all processors are sorted by order in ascending order.
         *
         * @return instance of update processor chain
         */
        public UpdateProcessorChain build() {
            List<UpdateProcessor> sortedUpdateProcessors = orderedUpdateProcessors.stream()
                    .sorted(Comparator.comparingInt(o -> o.order))
                    .map(orderedUpdateProcessor -> orderedUpdateProcessor.updateProcessor)
                    .toList();
            return new UpdateProcessorChain(sortedUpdateProcessors);
        }

        private record OrderedUpdateProcessor(UpdateProcessor updateProcessor, int order) {
        }
    }
}
