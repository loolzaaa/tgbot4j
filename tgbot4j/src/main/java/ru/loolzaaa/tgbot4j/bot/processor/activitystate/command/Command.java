package ru.loolzaaa.tgbot4j.bot.processor.activitystate.command;

import ru.loolzaaa.tgbot4j.core.api.types.Message;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

/**
 * This Interface represents the Command that can be executed
 */

public interface Command<T> {
    /**
     * Get the identifier of this command
     *
     * @return command identifier
     */
    String getIdentifier();

    CommandState<T> processCommand(MethodSender methodSender,
                                   Message message,
                                   String[] arguments,
                                   CommandState<?> currentState);
}
