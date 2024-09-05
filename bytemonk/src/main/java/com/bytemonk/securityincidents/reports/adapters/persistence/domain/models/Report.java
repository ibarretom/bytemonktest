package com.bytemonk.securityincidents.reports.adapters.persistence.domain.models;

import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import lombok.Getter;

import java.util.Date;

@Getter
public class Report {
    private int id;

    private final String title;

    private final String description;

    private final String severity;

    private final Date incidentDate;

    private Report(String aTitle, String aDescription, String aSeverity, Date aIncidentDate) {
        this.title = aTitle;
        this.description = aDescription;
        this.severity = aSeverity;
        this.incidentDate = aIncidentDate;
    }

    public static Report create(Incident aIncident) {
        return new Report(aIncident.getTitle().value(), aIncident.getDescription().value(),
                            aIncident.getSecurityLevel().toString(), aIncident.getIncidentDate().value());
    }
}
