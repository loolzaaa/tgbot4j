package ru.loolzaaa.tgbot4j.core.processor;


import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.sender.TelegramSender;

public interface UpdateProcessor {
    void process(Update update, TelegramSender telegramSender, UpdateProcessorChain chain);

    default int getOrder() {
        return 1;
    }
}
