package com.bytemonk.securityincidents.users.adapterts.persistense.database;

import com.bytemonk.securityincidents.users.IUserRepository;
import com.bytemonk.securityincidents.users.adapterts.persistense.domain.models.UserModel;
import com.bytemonk.securityincidents.users.domain.entities.User;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository {

    private final JpaAccessRepository jpaAccessRepository;

    @Autowired
    private UserRepository(JpaAccessRepository jpaAccessRepository) {
        this.jpaAccessRepository = jpaAccessRepository;
    }

    @Override
    public User findByUsername(Username username) {
        return UserModel.createDomain(jpaAccessRepository.findByUsername(username.value()));
    }
}
