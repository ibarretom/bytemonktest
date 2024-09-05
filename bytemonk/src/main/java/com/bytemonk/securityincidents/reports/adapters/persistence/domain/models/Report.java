package com.bytemonk.securityincidents.reports.adapters.persistence.domain.models;

import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Description;
import com.bytemonk.securityincidents.reports.domain.valueobjects.ESecurityLevel;
import com.bytemonk.securityincidents.reports.domain.valueobjects.HappenedAt;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Title;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;
import lombok.Getter;

import java.util.Date;

@Getter
public class Report {
    private Long id;

    private final String title;

    private final String description;

    private final String severity;

    private final Date incidentDate;

    private final String username;

    private Report(Long id, String aTitle, String aDescription, String aSeverity, Date aIncidentDate, String username) {
        this.id = id;
        this.title = aTitle;
        this.description = aDescription;
        this.severity = aSeverity;
        this.incidentDate = aIncidentDate;
        this.username = username;
    }

    public static Report create(Incident aIncident, Username username) {
        return new Report(null, aIncident.getTitle().value(), aIncident.getDescription().value(),
                          aIncident.getSecurityLevel().toString(),
                          aIncident.getIncidentDate().value(), username.value());
    }

    public static Incident createDomain(Report aReport) {
        return Incident.create(aReport.getId(), new Title(aReport.getTitle()), new Description(aReport.getDescription()),
                new HappenedAt(aReport.getIncidentDate(), -Incident.LIMIT_IN_DAYS),
                ESecurityLevel.valueOf(aReport.getSeverity()));
    }
}
