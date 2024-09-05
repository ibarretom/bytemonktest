package com.bytemonk.securityincidents.users.domain.valueobjects;

import com.bytemonk.securityincidents.abstractions.domain.services.Guard;
import lombok.SneakyThrows;

public record Name(String firstName, String lastName) {
    @SneakyThrows
    public Name {
            Guard.Against.NullOrEmpty(firstName);
            Guard.Against.NullOrEmpty(lastName);
    }
}
