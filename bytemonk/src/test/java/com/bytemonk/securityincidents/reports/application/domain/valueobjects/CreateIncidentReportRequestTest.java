package com.bytemonk.securityincidents.reports.application.domain.valueobjects;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.ValidationException;
import com.bytemonk.securityincidents.abstractions.domain.services.DateFactory;
import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Description;
import com.bytemonk.securityincidents.reports.domain.valueobjects.ESecurityLevel;
import com.bytemonk.securityincidents.reports.domain.valueobjects.HappenedAt;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Title;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CreateIncidentReportRequestTest {

    @Test
    void should_be_able_to_map_to_domain() {
        var aTitle = "Percy Jackson stole the Lightning";
        var aDescription = "The youngest son of Poseidon just stole, with his friends, the Zeus Lightning";
        var aDate = DateFactory.now();

        var aIncidentRequest = new CreateIncidentReportRequest(aTitle, aDescription, ESecurityLevel.HIGH.toString(), aDate);

        var aDomainIncident = CreateIncidentReportRequest.createDomain(aIncidentRequest);

        assertEquals(new Title(aTitle), aDomainIncident.getTitle());
        assertEquals(new Description(aDescription), aDomainIncident.getDescription());
        assertEquals(new HappenedAt(aDate, -Incident.LIMIT_IN_DAYS), aDomainIncident.getIncidentDate());
        assertEquals(ESecurityLevel.HIGH, aDomainIncident.getSecurityLevel());
        assertNull(aDomainIncident.getId());
    }

    @Test
    void should_thrown_when_title_filed_is_invalid() {
        assertThrows(ValidationException.class, () -> new CreateIncidentReportRequest("", "dwarf", ESecurityLevel.HIGH.toString(),DateFactory.now()));
    }

    @Test
    void should_thrown_when_description_filed_is_invalid() {
        var aTitle = "Jinx stole jace's orbit";
        var aLevel = ESecurityLevel.HIGH.toString();

        assertThrows(ValidationException.class, () -> new CreateIncidentReportRequest(aTitle, "",aLevel ,DateFactory.now()));
    }

    @Test
    void should_thrown_when_incident_date_filed_is_invalid() {
        var aTitle = "Jinx stole jace's orbit";
        var aDescription = "In a brand new attack Jinx with his associates just stole Jace's orbit";
        var aLevel = ESecurityLevel.HIGH;
        assertThrows(ValidationException.class, () -> new CreateIncidentReportRequest(aTitle,aDescription,aLevel.toString(), DateFactory.empty()));
    }

    @Test
    void should_thrown_when_incident_level_is_unknown() {
        var aTitle = "Mr. Robot strikes again";
        var aDescription = "The well known hacker Mr. Robot just invade the system. Be careful with your data";
        var aDate = DateFactory.now();

        assertThrows(ValidationException.class, () -> new CreateIncidentReportRequest(aTitle, aDescription, "SUPER_MASTER", aDate));
    }

    @Test
    void should_thrown_when_date_in_invalid() {
        var aTitle = "Slim shade made a bad joke";
        var aDescription = "In his last song, The deaf of Slim Shade, he just write a bad joke";
        var aDate = DateFactory.empty();
        var aLevel = ESecurityLevel.LOW.toString();

        assertThrows(ValidationException.class, () -> new CreateIncidentReportRequest(aTitle, aDescription, aLevel, aDate));
    }
}
