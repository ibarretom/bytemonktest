package com.bytemonk.securityincidents.reports.domain.services;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;

public interface IReportManager  {
    public Incident warnSecurityBreach(Incident aIncident, Username username) throws DomainException;
}
