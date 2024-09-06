package com.bytemonk.securityincidents.reports.application.services.usecases;

import com.bytemonk.securityincidents.abstractions.application.domain.valueobjects.UseCaseRequest;
import com.bytemonk.securityincidents.abstractions.application.services.UseCaseBase;
import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.abstractions.domain.exceptions.ValidationException;
import com.bytemonk.securityincidents.reports.application.domain.valueobjects.CreatedReportResponse;
import com.bytemonk.securityincidents.reports.application.domain.valueobjects.FetchAllRequestUseCase;
import com.bytemonk.securityincidents.reports.domain.services.IReportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FetchAllReports extends UseCaseBase {

    private final IReportManager reportManager;

    @Autowired
    public FetchAllReports(IReportManager reportManager) {
        this.reportManager = reportManager;
    }


    @Override
    protected List<CreatedReportResponse> process(UseCaseRequest aRequest) throws DomainException, ValidationException {
        var aFetchRequest = (FetchAllRequestUseCase) aRequest;

        var allIncidents = this.reportManager.findAll(aFetchRequest.getAnUser().getUsername());

        return allIncidents.stream().map(CreatedReportResponse::create).toList();
    }
}
