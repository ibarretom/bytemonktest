package com.bytemonk.securityincidents.reports.valueobjects;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DescriptionTest {
    @Test
    void should_allow_a_valid_description() {
        var aString = "Sauron, the enemy of middle Earth is back. Gather all the forces and lets stop him.";

        var aDescription = new Description(aString);

        assertEquals(aString, aDescription.value());
    }

    @ParameterizedTest
    @MethodSource("invalidEmptyFieldsProvider")
    void should_reject_empty_description(String aInvalidDescription) {
        assertThrows(DomainException.class, () -> new Description(aInvalidDescription));
    }

    private static Stream<String> invalidEmptyFieldsProvider() {
        return Stream.of(""," ", null);
    }
}
