package com.bytemonk.securityincidents.reports;

import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.reports.domain.valueobjects.ESecurityLevel;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Title;
import com.bytemonk.securityincidents.users.domain.entities.User;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;

import java.util.List;

public interface IIncidentReportRepository {
    Incident findByTitle(Title aTitle, Username anUsername);
    Incident saveIncident(Incident aIncident, User anUser);
    Incident findByIncidentId(Long id, Username anUsername);
    List<Incident> findAllIncidents(Username anUsername);
    Incident updateSeverity(Username anUsername, ESecurityLevel aSeverity, long anIncidentId);
}
