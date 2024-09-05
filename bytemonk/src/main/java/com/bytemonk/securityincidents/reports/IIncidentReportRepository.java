package com.bytemonk.securityincidents.reports;

import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Title;
import com.bytemonk.securityincidents.users.domain.entities.User;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;

public interface IIncidentReportRepository {
    public Incident findBy(Title aTitle, Username username);
    public Incident save(Incident aIncident, User user);
}
