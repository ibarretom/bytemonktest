package com.bytemonk.securityincidents.reports.adapters.presentation;


import com.bytemonk.securityincidents.abstractions.adapters.presentation.domain.ControllerResponse;
import com.bytemonk.securityincidents.abstractions.application.domain.valueobjects.ApplicationResponse;
import com.bytemonk.securityincidents.abstractions.application.domain.valueobjects.EOperationCode;
import com.bytemonk.securityincidents.reports.application.domain.valueobjects.CreateIncidentReportRequest;
import com.bytemonk.securityincidents.reports.application.domain.valueobjects.CreateReportUseCaseRequest;
import com.bytemonk.securityincidents.reports.application.domain.valueobjects.CreatedReportResponse;
import com.bytemonk.securityincidents.reports.application.services.usecases.CreateReportUseCase;
import com.bytemonk.securityincidents.users.domain.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/report")
public class ReportController {

    private CreateReportUseCase aCreateReportUseCase;

    @Autowired
    public ReportController(CreateReportUseCase createReportUseCase) {
        this.aCreateReportUseCase = createReportUseCase;
    }
    @PostMapping("")
    public ResponseEntity<ControllerResponse<CreatedReportResponse>> saveReport(HttpServletRequest request,
                                                            @RequestBody CreateIncidentReportRequest aIncidentReport) {
        var anUser = (User) request.getAttribute("authenticatedUser");

        var anApplicationResponse = (ApplicationResponse<CreatedReportResponse>) aCreateReportUseCase
                .execute(new CreateReportUseCaseRequest(anUser, aIncidentReport), EOperationCode.CREATED);

        return ResponseEntity
                    .status(anApplicationResponse.getHttpStatusFromCode())
                    .body(ControllerResponse.create(anApplicationResponse));
    }
}
