package com.bytemonk.securityincidents.reports.application.domain.valueobjects;

import com.bytemonk.securityincidents.abstractions.application.domain.valueobjects.UseCaseRequest;
import com.bytemonk.securityincidents.users.domain.entities.User;
import lombok.Getter;

@Getter
public class FetchReportByIdRequest extends UseCaseRequest {
    private final long aReportId;
    private final User anUser;

    public FetchReportByIdRequest(long aReportId, User anUser) {
        this.aReportId = aReportId;
        this.anUser = anUser;
    }
}
