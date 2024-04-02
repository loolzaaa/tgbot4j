package ru.loolzaaa.tgbot4j.core.exception;

import lombok.Getter;
import ru.loolzaaa.tgbot4j.core.api.ResponseWrapper;
import ru.loolzaaa.tgbot4j.core.api.types.ResponseParameters;

/**
 * This exception throws if some API request fails.
 * <p>
 * Exception instance can contain {@link ResponseParameters} object,
 * that describes why a request was unsuccessful.
 */

@Getter
public class ApiRequestException extends RuntimeException {

    private final Integer errorCode;
    private transient final ResponseParameters responseParameters;

    public ApiRequestException(ResponseWrapper responseWrapper) {
        super(responseWrapper.getDescription());
        this.errorCode = responseWrapper.getErrorCode();
        this.responseParameters = responseWrapper.getParameters();
    }

    public ApiRequestException(String message) {
        super(message);
        this.errorCode = null;
        this.responseParameters = null;
    }
}
