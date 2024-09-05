package com.bytemonk.securityincidents.reports.application.domain.valueobjects;

import com.bytemonk.securityincidents.reports.domain.entities.Incident;

import java.util.Date;

public record CreatedReportResponse(Long id, String title, String description, String severityLevel, Date incidentDate) {
    public static CreatedReportResponse create(Incident aReport) {
        return new CreatedReportResponse(aReport.getId(), aReport.getTitle().value(), aReport.getDescription().value(), aReport.getSecurityLevel().toString(), aReport.getIncidentDate().value());
    }
}
