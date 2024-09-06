package com.bytemonk.securityincidents.reports.application.domain.valueobjects;

import com.bytemonk.securityincidents.abstractions.application.domain.valueobjects.UseCaseRequest;
import com.bytemonk.securityincidents.reports.domain.valueobjects.ESecurityLevel;
import com.bytemonk.securityincidents.users.domain.entities.User;
import lombok.Getter;

@Getter
public class UpdateSeverityLevelUseCaseRequest extends UseCaseRequest {
    private long incidentId;
    private ESecurityLevel SeverityLevel;
    private User user;

    public UpdateSeverityLevelUseCaseRequest(long incidentId, ESecurityLevel SeverityLevel, User user) {
        this.incidentId = incidentId;
        this.SeverityLevel = SeverityLevel;
        this.user = user;
    }
}
