package ru.loolzaaa.tgbot4j.core.bot.processor;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public final class UpdateProcessorChain {

    private static final Logger log = LoggerFactory.getLogger(UpdateProcessorChain.class);

    private final List<UpdateProcessor> updateProcessors;

    private int currentProcessorPosition = 0;

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

    public static class Builder {

        private final List<OrderedUpdateProcessor> orderedUpdateProcessors = new ArrayList<>(4);

        public void addUpdateProcessor(UpdateProcessor updateProcessor, int order) {
            orderedUpdateProcessors.add(new OrderedUpdateProcessor(updateProcessor, order));
            log.info("Add new update processor {} with order {}", updateProcessor, order);
        }

        public UpdateProcessorChain build() {
            List<UpdateProcessor> sortedUpdateProcessors = orderedUpdateProcessors.stream()
                    .sorted(Comparator.comparingInt(o -> o.order))
                    .map(orderedUpdateProcessor -> orderedUpdateProcessor.updateProcessor)
                    .toList();
            return new UpdateProcessorChain(sortedUpdateProcessors);
        }

        @RequiredArgsConstructor
        private static class OrderedUpdateProcessor {
            private final UpdateProcessor updateProcessor;
            private final int order;
        }
    }
}
