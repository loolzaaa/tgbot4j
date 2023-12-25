package ru.loolzaaa.tgbot4j.core.processor;

import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.sender.TelegramSender;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UpdateProcessorChain {

    private final List<UpdateProcessor> updateProcessors = new ArrayList<>(4);

    private int currentProcessor = 0;

    public void init() {
        updateProcessors.sort(Comparator.comparingInt(UpdateProcessor::getOrder));
    }

    public void addUpdateProcess(UpdateProcessor updateProcessor) {
        updateProcessors.add(updateProcessor);
    }

    public void doProcess(Update update, TelegramSender telegramSender) {
        try {
            doProcessInternal(update, telegramSender);
        } catch (Exception e) {
            //
        } finally {
            //
        }
    }

    private void doProcessInternal(Update update, TelegramSender telegramSender) {
        if (currentProcessor == updateProcessors.size()) {
            return;
        }
        UpdateProcessor nextProcessor = updateProcessors.get(currentProcessor++);
//        if (logger.isTraceEnabled()) {
//            String name = nextFilter.getClass().getSimpleName();
//            logger.trace(LogMessage.format("Invoking %s (%d/%d)", name, this.currentPosition, this.size));
//        }
        nextProcessor.process(update, telegramSender, this);
    }
}
