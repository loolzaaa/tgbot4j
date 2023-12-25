package ru.loolzaaa.tgbot4j.core.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.sender.TelegramSender;

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

    public void doProcess(Update update, TelegramSender telegramSender) {
        try {
            doProcessInternal(update, telegramSender);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
    }

    private void doProcessInternal(Update update, TelegramSender telegramSender) {
        if (currentProcessor == updateProcessors.size()) {
            return;
        }
        UpdateProcessor nextProcessor = updateProcessors.get(currentProcessor++);
        if (log.isTraceEnabled()) {
            String name = nextProcessor.getClass().getSimpleName();
            log.trace("Invoking {} ({}/{})", name, currentProcessor, updateProcessors.size());
        }
        nextProcessor.process(update, telegramSender, this);
    }
}
