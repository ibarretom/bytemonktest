package com.bytemonk.securityincidents.reports.domain.services;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.reports.IIncidentReportRepository;
import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.users.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportManager implements IReportManager {
    private final IIncidentReportRepository incidentReportRepository;

    @Autowired
    public ReportManager(IIncidentReportRepository incidentReportRepository) {
        this.incidentReportRepository = incidentReportRepository;
    }

    public Incident warnSecurityBreach(Incident anIncident, User anUser) throws DomainException {
        var aFetchedIncident = incidentReportRepository.findByTitle(anIncident.getTitle(), anUser.getUsername());

        if (aFetchedIncident != null) {
            throw new DomainException("Report already made.");
        }

        return incidentReportRepository.saveIncident(anIncident, anUser);
    }
}
