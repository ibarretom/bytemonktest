package com.bytemonk.securityincidents.abstractions.domain.exceptions;

public class ValidationException extends IllegalArgumentException {
    public ValidationException(String message) {
        super(message);
    }
}
