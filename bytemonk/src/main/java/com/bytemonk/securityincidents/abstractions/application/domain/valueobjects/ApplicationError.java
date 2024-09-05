package com.bytemonk.securityincidents.abstractions.application.domain.valueobjects;

public final class ApplicationError<T> extends ApplicationResponse<T>{
    public ApplicationError(EOperationCode code, String message) {
        super(code, message);
    }
}
