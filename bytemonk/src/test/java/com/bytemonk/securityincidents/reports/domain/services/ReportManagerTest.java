package com.bytemonk.securityincidents.reports.domain.services;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.abstractions.domain.services.DateFactory;
import com.bytemonk.securityincidents.reports.adapters.persistence.database.IncidentReportRepositoryTest;
import com.bytemonk.securityincidents.reports.adapters.persistence.domain.models.Report;
import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Description;
import com.bytemonk.securityincidents.reports.domain.valueobjects.ESecurityLevel;
import com.bytemonk.securityincidents.reports.domain.valueobjects.HappenedAt;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Title;
import com.bytemonk.securityincidents.users.domain.entities.User;

import com.bytemonk.securityincidents.users.domain.valueobjects.Username;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ReportManagerTest {
    ReportManager reportManager;
    IncidentReportRepositoryTest repository;
    User anUser;

    @BeforeEach
    void init() {
        anUser = User.create("Bruce", "Wayne", "thebatman", "wayneenterprises"); ;
        repository = new IncidentReportRepositoryTest();
        this.reportManager = new ReportManager(repository);
    }

    @Test
    public void should_be_able_to_save_a_report() throws DomainException {
        var aTitle = "The Joker is loose";

        var aDescriptionString = "The joker just get out the Arkam Asilum I invoke all police forces to help in this search";

        var aDate = DateFactory.now();

        var aReport = Incident.create(aTitle, aDescriptionString, aDate, ESecurityLevel.MEDIUM);

        var aIncident = reportManager.warnSecurityBreach(aReport, anUser);

        assertEquals(new Title(aTitle),  aIncident.getTitle());
        assertEquals(new Description(aDescriptionString), aIncident.getDescription());
        assertEquals(new HappenedAt(aDate, -Incident.LIMIT_IN_DAYS), aIncident.getIncidentDate());
        assertEquals(ESecurityLevel.MEDIUM, aIncident.getSecurityLevel());
    }

    @Test
    public void should_throw_a_domain_exception_when_already_exists_a_report_for_username(){
        var aTitle = "Penguim just stole a password";
        var aDescriptionString = "One user left his browser open. What becomes a breach for the Penguim.";

        repository.saveIncident(Incident.create(aTitle, aDescriptionString, DateFactory.now(), ESecurityLevel.LOW), anUser);

        aDescriptionString = "The Penguim gather with Charede and broke a user account";

        var aDate = DateFactory.now();

        var aReport = Incident.create(aTitle, aDescriptionString, aDate, ESecurityLevel.HIGH);

        assertThrows(DomainException.class, () -> reportManager.warnSecurityBreach(aReport, anUser));
    }

    @Test
    void should_return_a_empty_list_when_no_incident_is_fount() throws DomainException {
        var anIncidentList = reportManager.findByIncidentId(12, new Username("batman"));

        assertEquals(0, anIncidentList.size());
    }
}
