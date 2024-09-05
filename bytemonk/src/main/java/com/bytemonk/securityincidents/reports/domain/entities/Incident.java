package com.bytemonk.securityincidents.reports.domain.entities;

import com.bytemonk.securityincidents.reports.domain.valueobjects.Description;
import com.bytemonk.securityincidents.reports.domain.valueobjects.ESecurityLevel;
import com.bytemonk.securityincidents.reports.domain.valueobjects.HappenedAt;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Title;
import lombok.Getter;

import java.util.Date;
@Getter
public final class Incident extends Report {
    public static final int LIMIT_IN_DAYS = 30;

    private final ESecurityLevel securityLevel;

    private final HappenedAt incidentDate;

    private Incident(Long id, Title title, Description description, HappenedAt happenedAt, ESecurityLevel securityLevel) {
        super(id, title, description);
        this.incidentDate = happenedAt;
        this.securityLevel = securityLevel;
    }

    public static Incident create(String title, String description, Date incidentDate, ESecurityLevel securityLevel) {
        return new Incident(null, new Title(title), new Description(description),
                                new HappenedAt(incidentDate, -Incident.LIMIT_IN_DAYS),
                                securityLevel);
    }

    public static Incident create(Long id, Title aTitle, Description aDescription, HappenedAt aIncidentDate, ESecurityLevel aSecurityLevel) {
        return new Incident(id, aTitle, aDescription, aIncidentDate, aSecurityLevel);
    }
}
