package com.bytemonk.securityincidents.reports.domain.valueobjects;

import com.bytemonk.securityincidents.abstractions.domain.services.Guard;
import lombok.SneakyThrows;

public record Title(String value) {

    @SneakyThrows
    public Title {
        int MIN_POSSIBLE_CHARS = 10;
        Guard.Against.SmallerThen(MIN_POSSIBLE_CHARS, value);
    }
}
