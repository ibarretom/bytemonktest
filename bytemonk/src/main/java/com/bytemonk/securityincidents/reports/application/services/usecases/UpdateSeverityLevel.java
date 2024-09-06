package com.bytemonk.securityincidents.reports.application.services.usecases;

import com.bytemonk.securityincidents.abstractions.application.domain.valueobjects.UseCaseRequest;
import com.bytemonk.securityincidents.abstractions.application.services.UseCaseBase;
import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.abstractions.domain.exceptions.ValidationException;
import com.bytemonk.securityincidents.reports.application.domain.valueobjects.CreatedReportResponse;
import com.bytemonk.securityincidents.reports.application.domain.valueobjects.UpdateSeverityLevelUseCaseRequest;
import com.bytemonk.securityincidents.reports.domain.services.IReportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateSeverityLevel extends UseCaseBase {
    private IReportManager reportManager;

    @Autowired
    public UpdateSeverityLevel(IReportManager reportManager) {
        this.reportManager = reportManager;
    }

    @Override
    protected CreatedReportResponse process(UseCaseRequest aRequest) throws DomainException, ValidationException {
        var aSeverityUpdate = (UpdateSeverityLevelUseCaseRequest) aRequest;

        var anIncident = reportManager.updateSeverity(aSeverityUpdate.getIncidentId(), aSeverityUpdate.getSeverityLevel(), aSeverityUpdate.getUser());

        return CreatedReportResponse.create(anIncident);
    }
}
