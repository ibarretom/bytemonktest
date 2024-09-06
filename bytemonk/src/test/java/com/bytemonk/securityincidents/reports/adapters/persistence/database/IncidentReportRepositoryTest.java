package com.bytemonk.securityincidents.reports.adapters.persistence.database;

import com.bytemonk.securityincidents.reports.IIncidentReportRepository;
import com.bytemonk.securityincidents.reports.adapters.persistence.domain.models.Report;
import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Title;
import com.bytemonk.securityincidents.users.domain.entities.User;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;

import java.util.ArrayList;
import java.util.List;

public class IncidentReportRepositoryTest implements IIncidentReportRepository {
    private final List<Report> reports;

    public IncidentReportRepositoryTest() {
        reports = new ArrayList<Report>();
    }

    @Override
    public Incident findByTitle(Title aTitle, Username anUsername) {
        var anIncident = reports.stream()
                .filter(report -> report.getTitle().equals(aTitle.value()) && report.getOwner().equals(anUsername))
                .findFirst();

        if (anIncident.isEmpty())
            return null;

        var aReport = anIncident.get();

        return Report.createDomain(aReport);
    }

    @Override
    public Incident saveIncident(Incident aIncident, User anUser) {
        var aReport = Report.create(aIncident, anUser);

        reports.add(aReport);

        return Report.createDomain(aReport);

    }

    @Override
    public Incident findByIncidentId(Long id, Username anUsername) {
        var aReport = reports.stream()
                .filter(report -> report.getId().equals(id) && report.getOwner().equals(anUsername))
                .findFirst();

        return aReport.map(Report::createDomain).orElse(null);

    }
}
