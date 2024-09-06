package com.bytemonk.securityincidents.reports.adapters.persistence.database;

import com.bytemonk.securityincidents.reports.IIncidentReportRepository;
import com.bytemonk.securityincidents.reports.adapters.persistence.domain.models.Report;
import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.reports.domain.valueobjects.ESecurityLevel;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Title;
import com.bytemonk.securityincidents.users.domain.entities.User;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReportsRepository implements IIncidentReportRepository {
    private IJpaAccess jpaRepository;

    @Autowired
    public ReportsRepository(IJpaAccess jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public Incident findByTitle(Title aTitle, Username anUsername) {
        var aReport = jpaRepository.findByTitle(aTitle.value(), anUsername.value());

        if (aReport == null) {
            return null;
        }

        return Report.createDomain(aReport);
    }

    public Incident saveIncident(Incident aIncident, User anUser) {
        try {
            var aSaving = jpaRepository.save(Report.create(aIncident, anUser));
            return Report.createDomain(aSaving);
        }catch(Exception e) {
            return null;
        }

    }

    @Override
    public Incident findByIncidentId(Long id, Username anUsername) {
        var all = jpaRepository.findAll();
        var aReport = jpaRepository.findByIncidentId(id, anUsername.value());

        if (aReport == null) {
            return null;
        }

        return Report.createDomain(aReport);
    }

    @Override
    public List<Incident> findAllIncidents(Username anUsername) {
        var aReportList = jpaRepository.findAllIncidents(anUsername.value());

        return aReportList.stream().map(Report::createDomain).toList();
    }

    @Override
    public Incident updateSeverity(Username anUsername, ESecurityLevel aSeverity, long anIncidentId) {
        var anUpdatedReport = jpaRepository.updateSeverity(anUsername.value(), aSeverity.toString(), anIncidentId);

        return Report.createDomain(anUpdatedReport);
    }
}
