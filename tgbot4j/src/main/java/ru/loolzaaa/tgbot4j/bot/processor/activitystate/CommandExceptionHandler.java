package ru.loolzaaa.tgbot4j.bot.processor.activitystate;

import ru.loolzaaa.tgbot4j.core.api.types.Update;
import ru.loolzaaa.tgbot4j.core.bot.sender.MethodSender;

/**
 * This interface is used for handling exceptions
 * while execution bot commands.
 * <p>
 * All exceptions it has thrown by {@link CommandStateUpdateProcessor}.
 */

@FunctionalInterface
public interface CommandExceptionHandler {
    /**
     * Handles execution exception.
     * <p>
     * It has access to an incoming update
     * and sender for some API methods usage.
     *
     * @param e            exception has thrown while command execution
     * @param update       incoming update
     * @param methodSender method sender for server communication
     */
    void handle(ProcessCommandException e, Update update, MethodSender methodSender);
}
