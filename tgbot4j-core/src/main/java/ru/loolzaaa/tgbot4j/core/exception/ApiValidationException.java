package ru.loolzaaa.tgbot4j.core.exception;

import lombok.Getter;
import ru.loolzaaa.tgbot4j.core.api.Validated;

/**
 * This exception throws when API object or method fails validation.
 * <p>
 * Exception instance can contain failed {@link Validated}.
 *
 * @see Validated
 */

@Getter
public class ApiValidationException extends RuntimeException {

    private transient final Validated target;

    public ApiValidationException(String message, Validated target) {
        super(message);
        this.target = target;
    }
}
