package com.bytemonk.securityincidents.reports;

import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Title;
import com.bytemonk.securityincidents.users.domain.entities.User;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;

public interface IIncidentReportRepository {
    Incident findByTitle(Title aTitle, Username anUsername);
    Incident saveIncident(Incident aIncident, User anUser);
}
