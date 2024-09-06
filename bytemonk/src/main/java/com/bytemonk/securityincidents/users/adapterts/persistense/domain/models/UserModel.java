package com.bytemonk.securityincidents.users.adapterts.persistense.domain.models;


import com.bytemonk.securityincidents.users.domain.entities.User;
import com.bytemonk.securityincidents.users.domain.valueobjects.Name;
import com.bytemonk.securityincidents.users.domain.valueobjects.Password;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;
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

    public static UserModel create(User anUser) {
        return new UserModel(anUser.getId(), anUser.getName().firstName(), anUser.getName().lastName(),
                anUser.getUsername().value(), anUser.getPassword().value());
    }

    public static User createDomain(UserModel anUser) {
        return User.create(anUser.getId(), new Name(anUser.firstName, anUser.lastName), new Username(anUser.username), new Password(anUser.password));
    }
}
