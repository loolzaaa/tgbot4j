package ru.loolzaaa.tgbot4j.bot.processor.activitystate;

import lombok.Getter;

/**
 * An exception that is thrown when a command is executed.
 * <p>
 * Contains the ID of the user and command
 * that caused the exception.
 */

@Getter
public class ProcessCommandException extends RuntimeException {

    /**
     * User identifier
     */
    private final Long userId;

    /**
     * Command identifier
     */
    private final String commandIdentifier;

    /**
     * Constructor creates exception instance based on user id,
     * command identifier and some message.
     *
     * @param message           exception message
     * @param userId            telegram id
     * @param commandIdentifier identifier of command
     */

    public ProcessCommandException(String message, Long userId, String commandIdentifier) {
        super(message);
        this.userId = userId;
        this.commandIdentifier = commandIdentifier;
    }
}
