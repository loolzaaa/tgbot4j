package ru.loolzaaa.tgbot4j.core.processor;


import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.sender.MethodSender;

public interface UpdateProcessor {
    void process(Update update, MethodSender methodSender, UpdateProcessorChain chain);

    default int getOrder() {
        return 1;
    }
}
