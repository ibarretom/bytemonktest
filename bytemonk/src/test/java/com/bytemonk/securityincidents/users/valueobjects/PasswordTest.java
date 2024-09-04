package com.bytemonk.securityincidents.users.valueobjects;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PasswordTest {
    @Test
    void should_allow_a_valid_password() {
        var aPassword = new Password("password");

        assertEquals("password", aPassword.value());
    }

    private static Stream<String> invalidPasswordProvider() {
        return Stream.of(""," ", null);
    }

    @ParameterizedTest
    @MethodSource("invalidPasswordProvider")
    void should_not_allow_a_null_or_empty_password(String anInvalidPassword) {
        assertThrows(DomainException.class, () -> new Password(anInvalidPassword));
    }
}
