package com.bytemonk.securityincidents.reports.application.domain.valueobjects;

import com.bytemonk.securityincidents.abstractions.domain.services.Guard;
import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.reports.domain.valueobjects.ESecurityLevel;
import lombok.SneakyThrows;

import java.util.Date;

public record CreateIncidentReportRequest(String title, String description, String severityLevel, Date incidentDate) {

    @SneakyThrows
    public CreateIncidentReportRequest {
        int MIN_SIZE = 10;
        Guard.Validate.SmallerThen(MIN_SIZE, title);
        Guard.Validate.NullOrEmpty(description);
        Guard.Validate.UnBounded(severityLevel, ESecurityLevel.class);
        Guard.Validate.OutsideLimit(incidentDate, Incident.getIncidentRangeLimit());
    }

    public static Incident createDomain(CreateIncidentReportRequest aReport) {
        var aLevel = ESecurityLevel.valueOf(aReport.severityLevel());
        return Incident.create(aReport.title, aReport.description, aReport.incidentDate, aLevel);
    }
}
