package com.bytemonk.securityincidents.users.domain;

import com.bytemonk.securityincidents.users.entities.User;
import com.bytemonk.securityincidents.users.valueobjects.Name;
import com.bytemonk.securityincidents.users.valueobjects.Password;
import com.bytemonk.securityincidents.users.valueobjects.Username;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserTest {
    @Test
    void should_be_able_to_create_a_user_without_id() {
        var aUser = User.create("Bilbo", "Baggins", "theringowner","hobbit");

        assertNull(aUser.getId());
    }

    @Test
    void should_be_able_to_create_a_user_with_the_right_fields() {
        var aUser = User.create("Frodo", "Baggins", "thechosen","hobbit");

        assertEquals(new Name("Frodo", "Baggins"), aUser.getName());
        assertEquals(new Username("thechosen"), aUser.getUsername());
        assertEquals(new Password("hobbit"), aUser.getPassword());
    }
}
