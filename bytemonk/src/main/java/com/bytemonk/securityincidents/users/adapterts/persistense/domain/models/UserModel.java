package com.bytemonk.securityincidents.users.adapterts.persistense.domain.models;


import com.bytemonk.securityincidents.users.domain.entities.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name="users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Getter
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private String username;

    @Column(name="password")
    private String password;

    private UserModel(Long id, String firstName, String lastName, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    protected UserModel() {}

    public static UserModel create(User aUser) {
        return new UserModel(aUser.getId(), aUser.getName().firstName(), aUser.getName().lastName(),
                            aUser.getUsername().value(), aUser.getPassword().value());
    }
}
