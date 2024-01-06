package ru.loolzaaa.tgbot4j.bot.processor.activitystate;

import lombok.Getter;

@Getter
public class ProcessCommandException extends RuntimeException {

    private final Long userId;

    private final String commandIdentifier;

    public ProcessCommandException(String message, Long userId, String commandIdentifier) {
        super(message);
        this.userId = userId;
        this.commandIdentifier = commandIdentifier;
    }
}
