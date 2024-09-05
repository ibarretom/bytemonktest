package com.bytemonk.securityincidents.reports.adapters.persistence.domain.models;

import com.bytemonk.securityincidents.abstractions.domain.services.DateFactory;
import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.reports.domain.valueobjects.ESecurityLevel;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReportTest {

    @Test
    void should_be_able_to_map_a_incident_report_to_db_model() {
        var aStringTitle = "Shade is back.";
        var aDescriptionTitle = "The old crazy rapper is back, please we need to take actions.";
        var aDate = DateFactory.now();

        var aIncident = Incident.create(aStringTitle, aDescriptionTitle, aDate, ESecurityLevel.HIGH);

        var aPersistentReport = Report.create(aIncident, new Username("Dr. Dre"));

        assertEquals(aStringTitle, aPersistentReport.getTitle());
        assertEquals(aDescriptionTitle, aPersistentReport.getDescription());
        assertEquals(aDate, aPersistentReport.getIncidentDate());
        assertEquals("HIGH", aPersistentReport.getSeverity());
        assertEquals("Dr. Dre", aPersistentReport.getUsername());
    }
}
