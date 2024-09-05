package com.bytemonk.securityincidents.reports.domain.services;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.reports.IIncidentReportRepository;
import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;

public class ReportManager implements IReportManager {
    private final IIncidentReportRepository incidentReportRepository;

    public ReportManager(IIncidentReportRepository incidentReportRepository) {
        this.incidentReportRepository = incidentReportRepository;
    }

    public Incident warnSecurityBreach(Incident aIncident, Username username) throws DomainException {
        var aFetchedIncident = incidentReportRepository.findBy(aIncident.getTitle(), username);

        if (aFetchedIncident != null) {
            throw new DomainException("Report already made.");
        }

        return incidentReportRepository.save(aIncident, username);
    }
}
