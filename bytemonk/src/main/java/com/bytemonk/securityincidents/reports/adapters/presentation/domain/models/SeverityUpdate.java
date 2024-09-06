package com.bytemonk.securityincidents.reports.adapters.presentation.domain.models;

import com.bytemonk.securityincidents.abstractions.domain.services.Guard;
import com.bytemonk.securityincidents.reports.domain.valueobjects.ESecurityLevel;
import lombok.Getter;

@Getter
public class SeverityUpdate {
    ESecurityLevel severity;

    public SeverityUpdate(ESecurityLevel severity) {
        this.severity = severity;
    }
}
