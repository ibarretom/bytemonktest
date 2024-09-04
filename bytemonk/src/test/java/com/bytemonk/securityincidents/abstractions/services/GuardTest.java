package com.bytemonk.securityincidents.abstractions.services;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.abstractions.domain.services.Guard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class GuardTest {
    @Test
    void should_accept_a_valid_string_for_not_null_or_empty() throws DomainException {
        var aName = "Galadriel";

        assertEquals(aName, Guard.Against.NullOrEmpty(aName));
    }

    @ParameterizedTest
    @MethodSource("invalidNamesProvider")
    void should_reject_an_invalid_string_for_not_null_or_empty(String anInvalidString) throws DomainException {
        assertThrows(DomainException.class, () -> Guard.Against.NullOrEmpty(anInvalidString));
    }

    private static Stream<String> invalidNamesProvider() {
        return Stream.of(""," ", null);
    }

}
