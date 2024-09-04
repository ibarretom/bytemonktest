package com.bytemonk.securityincidents.abstractions.domain.exceptions;

public class DomainException extends Exception {
    public DomainException() {
        super();
    }

    public DomainException(String message) {
        super(message);
    }
}
