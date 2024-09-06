package com.bytemonk.securityincidents.reports.domain.services;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.reports.IIncidentReportRepository;
import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.users.domain.entities.User;

public class ReportManager implements IReportManager {
    private final IIncidentReportRepository incidentReportRepository;

    public ReportManager(IIncidentReportRepository incidentReportRepository) {
        this.incidentReportRepository = incidentReportRepository;
    }

    public Incident warnSecurityBreach(Incident aIncident, User user) throws DomainException {
        var aFetchedIncident = incidentReportRepository.findByTitle(aIncident.getTitle(), user.getUsername());

        if (aFetchedIncident != null) {
            throw new DomainException("Report already made.");
        }

        return incidentReportRepository.saveIncident(aIncident, user);
    }
}
