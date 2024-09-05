package com.bytemonk.securityincidents.abstractions.valueobjects;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.abstractions.domain.services.Guard;
import lombok.SneakyThrows;

import java.util.Date;

public record DateRange(Date start, Date end) {
    @SneakyThrows
    public DateRange {
        Guard.Against.NullOrEmptyDate(start);
        Guard.Against.NullOrEmptyDate(end);

        if (start.after(end)) {
            throw new DomainException("Invalid date range");
        }
    }

    public boolean includes(Date aDate) {
        return !(isBeforeStart(aDate) || isAfterEnd(aDate));
    }

    private boolean isBeforeStart(Date aDate) {
        return aDate.before(start) && !aDate.equals(start);
    }

    private boolean isAfterEnd(Date aDate) {
        return aDate.after(end) && !aDate.equals(end);
    }

    public long getDays() {
        return (long) Math.nextUp(getDiff() / getConversionFactor());
    }

    private double getConversionFactor() {
        var TO_DAYS = 24.0;
        var TO_HOURS = 60;
        var TO_MINUTES = 60;
        var TO_SECONDS = 1000;

        return (TO_DAYS * TO_HOURS * TO_MINUTES * TO_SECONDS);
    }

    private long getDiff() {
        return end.getTime() - start.getTime();
    }
}
