package com.bytemonk.securityincidents.abstractions.domain.services;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DateFactoryTest {

    @ParameterizedTest
    @ValueSource(ints = {1, -1, 2, -2})
    void should_add_the_right_amount_of_time(int anIncrement) {
        var now = DateFactory.now();
        var aResult = DateFactory.add(now, anIncrement);

        assertEquals(getMilliseconds(now, anIncrement), aResult.getTime());
    }

    private long getMilliseconds(Date aDate, int anIncrement) {
        return aDate.getTime() + (long) anIncrement * 24 * 60 * 60 * 1000;
    }
}
