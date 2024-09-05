package com.bytemonk.securityincidents.abstractions.domain.services;

import java.util.Date;

public class DateFactory {
    private static final int TO_HOURS = 24;
    private static final int TO_MINUTES = 60;
    private static final int TO_SECONDS = 60;
    private static final int TO_MILLISECONDS = 1000;

    public static Date now() {
        return new Date();
    }

    public static Date add(Date aDate, int anIncrementInDays) {
        return new Date(parseToMilliseconds(aDate, anIncrementInDays));
    }

    private static long parseToMilliseconds(Date aDate, int anIncrement) {
        return aDate.getTime() + (long) anIncrement * TO_HOURS * TO_MINUTES * TO_SECONDS * TO_MILLISECONDS;
    }

    public static Date empty() {
        return new Date(0);
    }
}
