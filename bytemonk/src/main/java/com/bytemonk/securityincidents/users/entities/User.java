package com.bytemonk.securityincidents.users.entities;

import com.bytemonk.securityincidents.abstractions.domain.AggregateRoot;
import com.bytemonk.securityincidents.users.valueobjects.Name;
import com.bytemonk.securityincidents.users.valueobjects.Password;
import com.bytemonk.securityincidents.users.valueobjects.Username;
import lombok.Getter;
import lombok.Setter;

@Getter
public class User extends AggregateRoot {
    @Setter
    private Name name;

    private final Username username;

    @Setter
    private Password password;

    private User(Long id, Name name, Username username, Password password) {
        super(id);
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public static User create(String firstName, String lastName, String username, String password) {
        return new  User(null, new Name(firstName, lastName), new Username(username), new Password(password));
    }
}
