package com.bytemonk.securityincidents.reports.adapters.persistence.database;

import com.bytemonk.securityincidents.reports.adapters.persistence.domain.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IJpaAccess extends JpaRepository<Report, Long> {
    @Query(value = "SELECT i FROM Report i WHERE i.title = :title and i.user.username = :username")
    Report findByTitle(@Param("title") String aTitle, @Param("username") String aUsername);
}
