package com.bytemonk.securityincidents.reports.adapters.persistence.database;

import com.bytemonk.securityincidents.reports.IIncidentReportRepository;
import com.bytemonk.securityincidents.reports.adapters.persistence.domain.models.Report;
import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Title;
import com.bytemonk.securityincidents.users.domain.entities.User;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        var aReport = jpaRepository.findByIncidentId(id, anUsername.value());

        if (aReport == null) {
            return null;
        }

        return Report.createDomain(aReport);
    }
}
