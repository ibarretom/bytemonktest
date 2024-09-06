package com.bytemonk.securityincidents.reports.adapters.presentation;


import com.bytemonk.securityincidents.abstractions.adapters.presentation.domain.ControllerResponse;
import com.bytemonk.securityincidents.abstractions.application.domain.valueobjects.ApplicationResponse;
import com.bytemonk.securityincidents.abstractions.application.domain.valueobjects.EOperationCode;
import com.bytemonk.securityincidents.reports.application.domain.valueobjects.*;
import com.bytemonk.securityincidents.reports.application.services.usecases.CreateReportUseCase;
import com.bytemonk.securityincidents.reports.application.services.usecases.FetchReportByIdUseCase;
import com.bytemonk.securityincidents.users.domain.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/report", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {

    private CreateReportUseCase aCreateReportUseCase;
    private FetchReportByIdUseCase aFetchReportByIdUseCase;

    @Autowired
    public ReportController(CreateReportUseCase createReportUseCase, FetchReportByIdUseCase fetchReportByIdUseCase) {
        this.aCreateReportUseCase = createReportUseCase;
        this.aFetchReportByIdUseCase = fetchReportByIdUseCase;
    }
    @PostMapping("")
    public ResponseEntity<CreatedReportResponse> saveReport(HttpServletRequest request,
                                                            @RequestBody CreateIncidentReportRequest aIncidentReport) {
        var anUser = (User) request.getAttribute("authenticatedUser");

        var anApplicationResponse = (ApplicationResponse<CreatedReportResponse>) aCreateReportUseCase
                .execute(new CreateReportUseCaseRequest(anUser, aIncidentReport), EOperationCode.CREATED);

        return ResponseEntity
                .status(anApplicationResponse.getHttpStatusFromCode())
                .body(anApplicationResponse.getResult());
    }

    @GetMapping("{id}")
    public ResponseEntity<FetchReportResponse> fetchReportById(HttpServletRequest request,
                                                               @PathVariable long id) {
        var anUser = (User) request.getAttribute("authenticatedUser");

        var anDomainRequest = new FetchReportByIdRequest(id, anUser);
        var anApplicationResponse = (ApplicationResponse<FetchReportResponse>) aFetchReportByIdUseCase
                .execute(anDomainRequest, EOperationCode.FETCHED);

        return ResponseEntity
                .status(anApplicationResponse.getHttpStatusFromCode())
                .body(anApplicationResponse.getResult());
    }

    @GetMapping("")
    public ResponseEntity<List<CreatedReportResponse>> fetchAllReports(HttpServletRequest request) {
        var anUser = (User) request.getAttribute("authenticatedUser");

        var anDomainRequest = new FetchAllRequestUseCase(anUser);

        var anApplicationResponse = (ApplicationResponse<List<CreatedReportResponse>>) aFetchReportByIdUseCase
                .execute(anDomainRequest, EOperationCode.FETCHED);

        return ResponseEntity
                .status(anApplicationResponse.getHttpStatusFromCode())
                .body(anApplicationResponse.getResult());
    }
}
