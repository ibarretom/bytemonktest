package com.bytemonk.securityincidents.reports.valueobjects;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.abstractions.domain.services.DateFactory;
import com.bytemonk.securityincidents.abstractions.valueobjects.DateRange;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DateRangeTest {

    @Test
    void should_allow_a_valid_range() {
        var now = DateFactory.now();
        var aDayAfter = DateFactory.add(now, 2);

        var aRange = new DateRange(now, aDayAfter);

        assertEquals(now, aRange.start());
        assertEquals(aDayAfter, aRange.end());
    }

    @ParameterizedTest
    @MethodSource("invalidDateProvider")
    void should_reject_empty_dates_for_start_date(Date aInvalidDate) {
        assertThrows(DomainException.class, () -> new DateRange(aInvalidDate, DateFactory.now()));
    }

    private static Stream<Date> invalidDateProvider() {
        return Stream.of(null, new Date(0));
    }

    @ParameterizedTest
    @MethodSource("invalidDateProvider")
    void should_reject_empty_dates_for_start_end_date(Date aInvalidDate) {
        assertThrows(DomainException.class, () -> new DateRange(DateFactory.now(), aInvalidDate));
    }

    @Test
    void should_reject_date_range_that_start_is_after_end() {
        var now = DateFactory.now();
        var aDayBefore = DateFactory.add(now, -2);

        assertThrows(DomainException.class, () -> new DateRange(now, aDayBefore));
    }

    @Test
    void should_reject_date_range_that_end_is_before_start() {
        var now = DateFactory.now();
        var aDayAfter = DateFactory.add(now, 2);

        assertThrows(DomainException.class, () -> new DateRange(aDayAfter, now));
    }

    @Test
    void should_return_true_for_a_date_in_a_range() {
        var aDay = DateFactory.now();

        var aDayBefore = DateFactory.add(aDay, -2);
        var aDayAfter = DateFactory.add(aDay, 2);

        var aRange = new DateRange(aDayBefore, aDayAfter);

        assertTrue(aRange.includes(aDay));
    }

    @Test
    void should_return_false_for_a_date_outside_a_range() {
        var aDay = DateFactory.now();

        var aDayBefore = DateFactory.add(aDay, -2);
        var aDayAfter = DateFactory.add(aDay, -1);

        var aRange = new DateRange(aDayBefore, aDayAfter);

        assertFalse(aRange.includes(aDay));
    }

    @Test
    void should_return_the_correct_range_amount() {
        var aDay = DateFactory.now();
        var aDayBefore = DateFactory.add(aDay, -2);

        assertEquals(2, new DateRange(aDayBefore, aDay).getDays());
    }
}
