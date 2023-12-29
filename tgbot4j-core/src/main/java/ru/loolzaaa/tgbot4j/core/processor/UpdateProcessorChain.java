package ru.loolzaaa.tgbot4j.core.processor;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.sender.MethodSender;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class UpdateProcessorChain {

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

        private final List<UpdateProcessor> updateProcessors = new ArrayList<>(4);

        public void addUpdateProcess(UpdateProcessor updateProcessor) {
            updateProcessors.add(updateProcessor);
            log.info("Add new update processor: {}", updateProcessor);
        }

        public UpdateProcessorChain build() {
            updateProcessors.sort(Comparator.comparingInt(UpdateProcessor::getOrder));
            return new UpdateProcessorChain(updateProcessors);
        }
    }
}
