package com.bytemonk.securityincidents.abstractions.application.domain.valueobjects;

public final class ApplicationSuccess<T> extends ApplicationResponse<T>{
    public ApplicationSuccess(T result, EOperationCode code) {
        super(result, code);
    }
}
