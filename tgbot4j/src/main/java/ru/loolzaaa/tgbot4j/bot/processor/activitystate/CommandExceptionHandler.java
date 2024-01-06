package ru.loolzaaa.tgbot4j.bot.processor.activitystate;

import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

@FunctionalInterface
public interface CommandExceptionHandler {
    void handle(ProcessCommandException e, Update update, MethodSender methodSender);
}
