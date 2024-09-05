package com.bytemonk.securityincidents.reports.application.domain.valueobjects;

import com.bytemonk.securityincidents.abstractions.domain.services.Guard;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;
import lombok.SneakyThrows;

public record AuthenticatedUserRequest(String username, String password) {

    @SneakyThrows
    public AuthenticatedUserRequest {
        Guard.Validate.NullOrEmpty(username);
        Guard.Against.NullOrEmpty(password);
    }

    public Username getDomainUsername() {
        return new Username(username);
    }
}
