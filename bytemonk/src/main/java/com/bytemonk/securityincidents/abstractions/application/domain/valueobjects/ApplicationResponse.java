package com.bytemonk.securityincidents.abstractions.application.domain.valueobjects;

import lombok.Getter;

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
}
