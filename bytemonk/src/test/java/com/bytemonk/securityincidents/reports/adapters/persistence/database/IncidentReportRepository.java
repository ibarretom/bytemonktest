package com.bytemonk.securityincidents.reports.adapters.persistence.database;

import com.bytemonk.securityincidents.reports.IIncidentReportRepository;
import com.bytemonk.securityincidents.reports.adapters.persistence.domain.models.Report;
import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Title;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;

import java.util.ArrayList;
import java.util.List;

public class IncidentReportRepository implements IIncidentReportRepository {
    private final List<Report> reports;

    public IncidentReportRepository() {
        reports = new ArrayList<Report>();
    }

    @Override
    public Incident findBy(Title aTitle, Username username) {
        var aOptionalIncident = reports.stream().filter(report -> report.getTitle().equals(aTitle.value()) && report.getUsername().equals(username.value()))
                .findFirst();

        if (aOptionalIncident.isEmpty())
            return null;

        var aReport = aOptionalIncident.get();

        return Report.createDomain(aReport);
    }

    @Override
    public Incident save(Incident aIncident, Username username) {
        var aReport = Report.create(aIncident, username);

        reports.add(aReport);

        return Report.createDomain(aReport);


    }
}
