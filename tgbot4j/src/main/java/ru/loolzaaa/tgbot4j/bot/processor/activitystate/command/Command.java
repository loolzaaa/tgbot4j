package ru.loolzaaa.tgbot4j.bot.processor.activitystate.command;

import ru.loolzaaa.tgbot4j.core.api.types.Message;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

import java.util.List;

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

    /**
     * Get the description of this command
     *
     * @return command description
     */
    String getDescription();

    CommandState<T> processCommand(MethodSender methodSender,
                                   Message message,
                                   List<String> arguments,
                                   CommandState<T> currentState);
}
