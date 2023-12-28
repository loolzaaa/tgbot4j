package ru.loolzaaa.tgbot4j.core.exception;

import lombok.Getter;
import ru.loolzaaa.tgbot4j.core.api.Validated;

@Getter
public class ApiValidationException extends RuntimeException {

    private final Validated target;

    public ApiValidationException(String message, Validated target) {
        super(message);
        this.target = target;
    }
}
