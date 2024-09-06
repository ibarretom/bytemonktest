package com.bytemonk.securityincidents.reports.application.domain.valueobjects;

import com.bytemonk.securityincidents.abstractions.application.domain.valueobjects.UseCaseRequest;
import com.bytemonk.securityincidents.users.domain.entities.User;
import lombok.Getter;

@Getter
public class FetchAllRequestUseCase extends UseCaseRequest {
    private User anUser;

    public FetchAllRequestUseCase(User anUser) {
        this.anUser = anUser;
    }
}
