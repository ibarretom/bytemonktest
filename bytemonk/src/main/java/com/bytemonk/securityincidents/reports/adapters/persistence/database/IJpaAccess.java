package com.bytemonk.securityincidents.reports.adapters.persistence.database;

import com.bytemonk.securityincidents.reports.adapters.persistence.domain.models.Report;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IJpaAccess extends JpaRepository<Report, Long> {
    @Query(value = "SELECT i FROM Report i WHERE i.title = :title and i.user.username = :username")
    Report findByTitle(@Param("title") String aTitle, @Param("username") String anUsername);

    @Query(value = "SELECT r FROM Report r WHERE r.id = :id AND r.user.username = :username")
    Report findByIncidentId(@Param("id") Long aId, @Param("username") String anUsername);

    @Query(value = "SELECT r from Report r WHERE r.user.username = :username")
    List<Report> findAllIncidents(@Param("username") String anUsername);

    @Transactional
    @Modifying
    @Query("UPDATE Report r SET r.severity = :serverity WHERE r.user.username = :username AND r.id = :id")
    Report updateSeverity(@Param("username") String anUsername, @Param("serverity") String serverity, @Param("id") Long id);
}
