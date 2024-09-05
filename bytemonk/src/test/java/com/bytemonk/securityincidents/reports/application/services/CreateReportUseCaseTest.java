package com.bytemonk.securityincidents.reports.application.services;

import com.bytemonk.securityincidents.abstractions.application.domain.valueobjects.EOperationCode;
import com.bytemonk.securityincidents.abstractions.domain.services.DateFactory;
import com.bytemonk.securityincidents.reports.adapters.persistence.database.IncidentReportRepositoryTest;
import com.bytemonk.securityincidents.reports.application.domain.valueobjects.CreateIncidentReportRequest;
import com.bytemonk.securityincidents.reports.application.domain.valueobjects.CreateReportUseCaseRequest;

import com.bytemonk.securityincidents.reports.application.domain.valueobjects.CreatedReportResponse;
import com.bytemonk.securityincidents.reports.application.services.usecases.CreateReportUseCase;
import com.bytemonk.securityincidents.reports.domain.services.ReportManager;
import com.bytemonk.securityincidents.users.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CreateReportUseCaseTest {
    private User aUser;
    private ReportManager reportManager;
    CreateReportUseCase aUseCase;
    @BeforeEach
    void init() {
        aUser = User.create("Berry", "Allen", "fastestmanalive", "zipzip");
        reportManager = new ReportManager(new IncidentReportRepositoryTest());
        aUseCase = new CreateReportUseCase(reportManager);
    }

    @Test
    void should_be_able_to_create_report() {
        var aTitle = "Dr. Wells just seems to be reverse flash.";
        var aDescription = "The evidences are not clear but seems that he is this man. Be awere.";
        var aReportToCreate = new CreateIncidentReportRequest(aTitle, aDescription, "LOW", DateFactory.now());

        var aResponse = aUseCase.execute(new CreateReportUseCaseRequest(aUser, aReportToCreate), EOperationCode.CREATED);

        var aResponseObject = (CreatedReportResponse) aResponse.getResult();

        assertTrue(aResponse.isSuccess());

        assertEquals(aTitle, aResponseObject.title());
    }
}
