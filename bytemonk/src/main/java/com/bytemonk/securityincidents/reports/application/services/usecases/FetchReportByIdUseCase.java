package com.bytemonk.securityincidents.reports.application.services.usecases;

import com.bytemonk.securityincidents.abstractions.application.domain.valueobjects.UseCaseRequest;
import com.bytemonk.securityincidents.abstractions.application.services.UseCaseBase;
import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.abstractions.domain.exceptions.ValidationException;
import com.bytemonk.securityincidents.reports.application.domain.valueobjects.CreatedReportResponse;
import com.bytemonk.securityincidents.reports.application.domain.valueobjects.FetchReportByIdRequest;
import com.bytemonk.securityincidents.reports.application.domain.valueobjects.FetchReportResponse;
import com.bytemonk.securityincidents.reports.domain.services.IReportManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class FetchReportByIdUseCase extends UseCaseBase {
    private IReportManager reportManager;

    @Autowired
    public FetchReportByIdUseCase(IReportManager reportManager) {
        this.reportManager = reportManager;
    }

    public FetchReportByIdUseCase() {}

    @Override
    protected FetchReportResponse process(UseCaseRequest aRequest) throws DomainException, ValidationException {
        var aReportRequest = (FetchReportByIdRequest) aRequest;

        var aReportList = reportManager
                            .findByIncidentId(aReportRequest.getAReportId(), aReportRequest.getAnUser().getUsername());

        return new FetchReportResponse(aReportList.stream()
                                    .map(CreatedReportResponse::create).collect(Collectors.toList()));
    }
}
