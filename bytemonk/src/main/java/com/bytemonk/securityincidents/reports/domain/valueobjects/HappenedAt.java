package com.bytemonk.securityincidents.reports.domain.valueobjects;

import com.bytemonk.securityincidents.abstractions.domain.services.DateFactory;
import com.bytemonk.securityincidents.abstractions.domain.services.Guard;
import com.bytemonk.securityincidents.abstractions.valueobjects.DateRange;
import lombok.SneakyThrows;

import java.util.Date;

public record HappenedAt(Date value, int aLimitInDays) {

    @SneakyThrows
    public HappenedAt {
        var now = DateFactory.now();

        var aRange = new DateRange(DateFactory.add(now, aLimitInDays), now);

        Guard.Against.OutsideLimit(value, aRange);
    }
}
