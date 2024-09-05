package com.bytemonk.securityincidents.reports.domain;

import com.bytemonk.securityincidents.abstractions.domain.services.DateFactory;
import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Description;
import com.bytemonk.securityincidents.reports.domain.valueobjects.ESecurityLevel;
import com.bytemonk.securityincidents.reports.domain.valueobjects.HappenedAt;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Title;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class IncidentTest {

    @Test
    void should_be_able_to_create_a_incident() {
        var aTitleString = "Arsene lupin stole the black pearl";
        var aDescriptionString = "The well knwon Arsene lupin stole the black pearl in the face of all police force";
        var aDate = DateFactory.now();

        var anIncident = Incident.create(aTitleString, aDescriptionString, aDate, ESecurityLevel.HIGH);

        assertEquals(new Title(aTitleString), anIncident.getTitle());
        assertEquals(new Description(aDescriptionString), anIncident.getDescription());
        assertEquals(new HappenedAt(aDate, -Incident.LIMIT_IN_DAYS), anIncident.getIncidentDate());
        assertEquals(ESecurityLevel.HIGH, anIncident.getSecurityLevel());
    }

    @Test
    void should_create_an_incident_with_null_id() {
        var aTitleString = "Sergio, The Professor is back with his crew";
        var aDescriptionString = "The professor is back with his crew for one more heist. Please send all forces to backup me";
        var aDate = DateFactory.now();

        var anIncident = Incident.create(aTitleString, aDescriptionString, aDate, ESecurityLevel.MEDIUM);

        assertNull(anIncident.getId());
    }
}
