package com.bytemonk.securityincidents.reports.application.domain.valueobjects;

import com.bytemonk.securityincidents.users.domain.valueobjects.Username;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuthenticatedUserRequestTest {

    @Test
    public void should_be_able_to_retrieve_a_user_name() {
        var aRequestUser = new AuthenticatedUserRequest("Claire", "theblondwoman");

        assertEquals(new Username("Claire"), aRequestUser.getDomainUsername());
    }
}
