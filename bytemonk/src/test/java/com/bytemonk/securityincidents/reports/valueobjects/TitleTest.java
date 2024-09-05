package com.bytemonk.securityincidents.reports.valueobjects;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Title;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TitleTest {
    @Test
    void should_allow_a_valid_title() {
        var aTitle = new Title("Sauron is back");

        assertEquals("Sauron is back", aTitle.aValue());
    }

    @ParameterizedTest
    @MethodSource("invalidTitlesProvider")
    void should_not_allow_null_or_empty_titles(String anInvalidTitle) {
        assertThrows(DomainException.class, () -> new Title(anInvalidTitle));
    }

    private static Stream<String> invalidTitlesProvider() {
        return Stream.of(""," ", null);
    }
}
