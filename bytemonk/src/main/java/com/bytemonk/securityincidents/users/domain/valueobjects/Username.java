package com.bytemonk.securityincidents.users.domain.valueobjects;

import com.bytemonk.securityincidents.abstractions.domain.services.Guard;
import lombok.SneakyThrows;

public record Username(String value) {
    @SneakyThrows
    public Username {
        Guard.Against.NullOrEmpty(value);
    }
}
