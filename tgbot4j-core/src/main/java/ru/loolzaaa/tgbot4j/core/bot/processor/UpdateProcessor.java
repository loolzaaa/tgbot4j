package ru.loolzaaa.tgbot4j.core.bot.processor;


import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

public interface UpdateProcessor {
    void process(Update update, MethodSender methodSender, UpdateProcessorChain chain);
}
