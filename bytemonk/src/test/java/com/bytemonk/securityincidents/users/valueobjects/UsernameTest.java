package com.bytemonk.securityincidents.users.valueobjects;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UsernameTest {

    @Test
    void should_allow_a_valid_username() {
        var aUsername = new Username("elrondtheelf");

        assertEquals("elrondtheelf", aUsername.value());
    }

    private static Stream<String> invalidUsernameProvider() {
        return Stream.of(""," ", null);
    }

    @ParameterizedTest
    @MethodSource("invalidUsernameProvider")
    void should_reject_an_invalid_username(String invalidUsername) {
        assertThrows(DomainException.class, () -> new Username(invalidUsername));
    }
}
