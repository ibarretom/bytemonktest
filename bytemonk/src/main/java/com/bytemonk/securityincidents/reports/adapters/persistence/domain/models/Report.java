package com.bytemonk.securityincidents.reports.adapters.persistence.domain.models;

import com.bytemonk.securityincidents.reports.domain.entities.Incident;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Description;
import com.bytemonk.securityincidents.reports.domain.valueobjects.ESecurityLevel;
import com.bytemonk.securityincidents.reports.domain.valueobjects.HappenedAt;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Title;
import com.bytemonk.securityincidents.users.adapterts.persistense.domain.models.UserModel;
import com.bytemonk.securityincidents.users.domain.entities.User;
import com.bytemonk.securityincidents.users.domain.valueobjects.Username;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
@Table(name ="reports", uniqueConstraints = @UniqueConstraint(columnNames={"title"}))

@Getter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private String severity;

    @Column(name = "incident_date")
    private Date incidentDate;

    @ManyToOne
    @JoinColumn(name="username", insertable = false, updatable = false)
    private UserModel user;

    private Report(Long id, String aTitle, String aDescription, String aSeverity, Date aIncidentDate, User aUser) {
        this.id = id;
        this.title = aTitle;
        this.description = aDescription;
        this.severity = aSeverity;
        this.incidentDate = aIncidentDate;
        this.user = UserModel.create(aUser);
    }

    protected Report() {}

    public Username getOwner() {
        return new Username(this.user.getUsername());
    }
    public static Report create(Incident aIncident, User user) {
        return new Report(null, aIncident.getTitle().value(), aIncident.getDescription().value(),
                aIncident.getSecurityLevel().toString(),
                aIncident.getIncidentDate().value(), user);
    }

    public static Incident createDomain(Report aReport) {
        return Incident.create(aReport.getId(), new Title(aReport.getTitle()), new Description(aReport.getDescription()),
                new HappenedAt(aReport.getIncidentDate(), -Incident.LIMIT_IN_DAYS),
                ESecurityLevel.valueOf(aReport.getSeverity()));
    }
}
