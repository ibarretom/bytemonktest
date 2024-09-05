package com.bytemonk.securityincidents.reports.domain.services;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.users.domain.entities.User;

public interface IReportManager  {
    public Incident warnSecurityBreach(Incident aIncident, User user) throws DomainException;
}
