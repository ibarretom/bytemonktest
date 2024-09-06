package com.bytemonk.securityincidents.users.adapterts.persistense.database;

import com.bytemonk.securityincidents.users.adapterts.persistense.domain.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaAccessRepository extends JpaRepository<UserModel, Long> {
    @Query(value= "SELECT u FROM UserModel u where u.username = :username")
    UserModel findByUsername(@Param("username") String username);
}
