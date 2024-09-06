package com.bytemonk.securityincidents.reports.domain.services;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.reports.IIncidentReportRepository;
import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.users.domain.entities.User;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Incident> findByIncidentId(long anId, Username anUsername) throws DomainException {
        var anIncident = incidentReportRepository.findByIncidentId(anId, anUsername);

        if (anIncident == null) {
            return new ArrayList<Incident>();
        }

        return List.of(anIncident);
    }

    @Override
    public List<Incident> findAll(Username anUsername) throws DomainException {
        return List.of();
    }
}
