package com.bytemonk.securityincidents.reports.application.domain.valueobjects;

import com.bytemonk.securityincidents.abstractions.domain.services.DateFactory;
import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Description;
import com.bytemonk.securityincidents.reports.domain.valueobjects.ESecurityLevel;
import com.bytemonk.securityincidents.reports.domain.valueobjects.HappenedAt;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Title;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CreatedReportResponseTest {

    @Test
    void should_be_able_to_be_created_by_a_domain_value() {
        var aTitle = new Title("Pablo Escobar was sighted outside prison");
        var aDescription = new Description("He was with seen with two other fellas at the Mint");
        var aIncidentDate = new HappenedAt(DateFactory.now(), -Incident.LIMIT_IN_DAYS);
        var aLevel = ESecurityLevel.HIGH;

        var aIncident = Incident.create((long)12, aTitle, aDescription, aIncidentDate, aLevel);

        var aResponse = CreatedReportResponse.create(aIncident);

        assertEquals(aTitle.value(), aResponse.title());
        assertEquals(aDescription.value(), aResponse.description());
        assertEquals(aIncidentDate.value(), aResponse.incidentDate());
        assertEquals(aLevel.toString(), aResponse.severityLevel());
    }
}
