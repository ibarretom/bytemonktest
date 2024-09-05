package com.bytemonk.securityincidents.reports.valueobjects;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.abstractions.domain.services.DateFactory;
import com.bytemonk.securityincidents.reports.domain.valueobjects.HappenedAt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class HappenedAtTest {
    @Test
    void should_allow_a_valid_date() throws DomainException {
        var aDate = DateFactory.now();

        var anEventHappenedAt = new HappenedAt(aDate, -30);

        assertEquals(aDate, anEventHappenedAt.value());
    }

    @Test
    void should_not_allow_a_date_after_today() throws DomainException {
        var aDate = DateFactory.now();

        assertThrows(DomainException.class, () -> new HappenedAt(DateFactory.add(aDate, 1), -30));
    }
}
