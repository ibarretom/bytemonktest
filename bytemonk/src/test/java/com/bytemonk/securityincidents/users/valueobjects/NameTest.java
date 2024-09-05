package com.bytemonk.securityincidents.users.valueobjects;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.users.domain.valueobjects.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class NameTest {

    @Test
    void should_allow_a_valid_name() {
        var aName = new Name("John", "Wick");

        assertEquals("John", aName.firstName());
        assertEquals("Wick", aName.lastName());
    }

    @ParameterizedTest
    @MethodSource("invalidNamesProvider")
    void should_not_allow_empty_or_null_first_name(String anInvalidName) {
        assertThrows(DomainException.class, () -> new Name(anInvalidName, "Valid"));
    }

    private static Stream<String> invalidNamesProvider() {
        return Stream.of(""," ", null);
    }

    @ParameterizedTest
    @MethodSource("invalidNamesProvider")
    void should_not_allow_empty_or_null_last_name(String anInvalidName) {
        assertThrows(DomainException.class, () -> new Name("Valid", anInvalidName));
    }
}
