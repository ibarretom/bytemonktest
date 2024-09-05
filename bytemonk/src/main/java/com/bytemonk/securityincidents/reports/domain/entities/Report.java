package com.bytemonk.securityincidents.reports.domain.entities;

import com.bytemonk.securityincidents.abstractions.domain.Entity;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Description;
import com.bytemonk.securityincidents.reports.domain.valueobjects.Title;
import lombok.Getter;
import lombok.Setter;

@Getter
public sealed abstract class Report extends Entity permits Incident {
    private final Title title;

    @Setter
    private Description description;

    protected Report(Long id, Title title, Description description) {
        super(id);
        this.title = title;
        this.description = description;
    }
}
