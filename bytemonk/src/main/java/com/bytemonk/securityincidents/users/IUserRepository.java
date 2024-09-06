package com.bytemonk.securityincidents.users;

import com.bytemonk.securityincidents.users.domain.entities.User;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;

public interface IUserRepository {
    public User findByUsername(Username username);
}
