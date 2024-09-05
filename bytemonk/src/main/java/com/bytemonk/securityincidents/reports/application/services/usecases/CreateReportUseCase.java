package com.bytemonk.securityincidents.reports.application.services.usecases;

import com.bytemonk.securityincidents.abstractions.application.domain.valueobjects.UseCaseRequest;
import com.bytemonk.securityincidents.abstractions.application.services.UseCaseBase;
import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.abstractions.domain.exceptions.ValidationException;
import com.bytemonk.securityincidents.reports.application.domain.valueobjects.CreateIncidentReportRequest;
import com.bytemonk.securityincidents.reports.application.domain.valueobjects.CreateReportUseCaseRequest;
import com.bytemonk.securityincidents.reports.application.domain.valueobjects.CreatedReportResponse;
import com.bytemonk.securityincidents.reports.domain.services.IReportManager;

public class CreateReportUseCase extends UseCaseBase {
    private final IReportManager reportManager;

    public CreateReportUseCase(IReportManager reportManager) {
        this.reportManager = reportManager;
    }

    @Override
    protected CreatedReportResponse process(UseCaseRequest aRequest) throws DomainException, ValidationException {
        var parsedRequest = (CreateReportUseCaseRequest) aRequest;

        var aReportRequest = parsedRequest.getCreateIncidentReportRequest();
        var anUser = parsedRequest.getAuthenticatedUserRequest();
        var aCreatedReport = reportManager.warnSecurityBreach(CreateIncidentReportRequest.createDomain(aReportRequest), anUser);

        return CreatedReportResponse.create(aCreatedReport);
    }
}

