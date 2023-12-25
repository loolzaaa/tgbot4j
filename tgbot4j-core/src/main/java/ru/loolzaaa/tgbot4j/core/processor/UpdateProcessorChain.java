package ru.loolzaaa.tgbot4j.core.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.sender.MethodSender;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UpdateProcessorChain {

    private static final Logger log = LoggerFactory.getLogger(UpdateProcessorChain.class);

    private final List<UpdateProcessor> updateProcessors = new ArrayList<>(4);

    private int currentProcessor = 0;

    public void addUpdateProcess(UpdateProcessor updateProcessor) {
        updateProcessors.add(updateProcessor);
        updateProcessors.sort(Comparator.comparingInt(UpdateProcessor::getOrder));
        log.info("Add new update processor: {}", updateProcessor);
    }

    public void doProcess(Update update, MethodSender methodSender) {
        try {
            doProcessInternal(update, methodSender);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
    }

    private void doProcessInternal(Update update, MethodSender methodSender) {
        if (currentProcessor == updateProcessors.size()) {
            return;
        }
        UpdateProcessor nextProcessor = updateProcessors.get(currentProcessor++);
        if (log.isTraceEnabled()) {
            String name = nextProcessor.getClass().getSimpleName();
            log.trace("Invoking {} ({}/{})", name, currentProcessor, updateProcessors.size());
        }
        nextProcessor.process(update, methodSender, this);
    }
}
