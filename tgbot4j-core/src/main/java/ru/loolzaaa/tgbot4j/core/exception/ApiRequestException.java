package ru.loolzaaa.tgbot4j.core.exception;

import lombok.Getter;
import ru.loolzaaa.tgbot4j.core.api.ResponseWrapper;
import ru.loolzaaa.tgbot4j.core.api.types.ResponseParameters;

@Getter
public class ApiRequestException extends RuntimeException {

    private Integer errorCode;
    private ResponseParameters responseParameters;

    public ApiRequestException(ResponseWrapper responseWrapper) {
        super(responseWrapper.getDescription());
        this.errorCode = responseWrapper.getErrorCode();
        this.responseParameters = responseWrapper.getParameters();
    }

    public ApiRequestException(String message) {
        super(message);
    }
}
