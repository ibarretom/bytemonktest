package com.bytemonk.securityincidents.users.valueobjects;

import com.bytemonk.securityincidents.abstractions.domain.services.Guard;
import lombok.SneakyThrows;

public record Password(String value) {
    @SneakyThrows
    public Password {
        Guard.Against.NullOrEmpty(value);
    }
}
