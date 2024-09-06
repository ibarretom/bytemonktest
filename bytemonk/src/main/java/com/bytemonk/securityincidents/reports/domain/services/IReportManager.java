package com.bytemonk.securityincidents.reports.domain.services;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.reports.domain.valueobjects.ESecurityLevel;
import com.bytemonk.securityincidents.users.domain.entities.User;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;

import java.util.List;

public interface IReportManager  {
    public Incident warnSecurityBreach(Incident anIncident, User anUser) throws DomainException;
    public List<Incident> findByIncidentId(long anId, Username anUsername) throws DomainException;
    public List<Incident> findAll(Username anUsername) throws DomainException;
    public Incident updateSeverity(long anIncidentId, ESecurityLevel severity, User anUser) throws DomainException;
}
