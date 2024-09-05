package com.bytemonk.securityincidents.reports.domain.valueobjects;

import com.bytemonk.securityincidents.abstractions.domain.services.Guard;
import lombok.SneakyThrows;

public record Description(String value) {

    @SneakyThrows
    public Description {
        Guard.Against.NullOrEmpty(value);
    }
}
