package com.bytemonk.securityincidents.reports.application.domain.valueobjects;

import com.bytemonk.securityincidents.abstractions.application.domain.valueobjects.UseCaseRequest;
import com.bytemonk.securityincidents.users.domain.entities.User;
import lombok.Getter;

@Getter
public class CreateReportUseCaseRequest extends UseCaseRequest {
    User authenticatedUserRequest;
    CreateIncidentReportRequest createIncidentReportRequest;

    public CreateReportUseCaseRequest(User authenticatedUserRequest, CreateIncidentReportRequest createIncidentReportRequest) {
        this.authenticatedUserRequest = authenticatedUserRequest;
        this.createIncidentReportRequest = createIncidentReportRequest;
    }
}
