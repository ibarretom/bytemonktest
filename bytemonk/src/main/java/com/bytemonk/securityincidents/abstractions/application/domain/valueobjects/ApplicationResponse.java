package com.bytemonk.securityincidents.abstractions.application.domain.valueobjects;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract sealed class ApplicationResponse<T> permits ApplicationSuccess, ApplicationError {
    private final T result;
    private final String message;
    private final EOperationCode code;

    public static <T> ApplicationSuccess<T> success(T aResult, EOperationCode aCode) {
        return new ApplicationSuccess<T>(aResult, aCode);
    }

    public static <T> ApplicationError<T> error(String aMessage, EOperationCode aCode) {
        return new ApplicationError<T>(aCode, aMessage);
    }

    protected ApplicationResponse(T result, EOperationCode code) {
        this.result = result;
        this.message = "";
        this.code = code;
    }

    protected ApplicationResponse(EOperationCode code, String message) {
        this.result = null;
        this.message = message;
        this.code = code;
    }

    public boolean isSuccess() {
        return result != null;
    }

    public HttpStatus getHttpStatusFromCode() {
        if (code.equals(EOperationCode.DOMAIN_ERROR) || code.equals(EOperationCode.INTERNAL_ERROR)) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        if (code.equals(EOperationCode.USER_INPUT_ERROR)) {
            return HttpStatus.BAD_REQUEST;
        }

        if (code.equals(EOperationCode.DEFAULT_SUCCESS))
            return HttpStatus.OK;

        if (code.equals(EOperationCode.CREATED)) {
            return HttpStatus.CREATED;
        }

        if (code.equals(EOperationCode.UPDATED)) {
            return HttpStatus.OK;
        }

        return HttpStatus.OK;
    }
}
