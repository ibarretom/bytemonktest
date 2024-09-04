package com.bytemonk.securityincidents.abstractions.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot extends Entity {
    protected ArrayList<IDomainEvent> domainEvents;

    public AggregateRoot(long id) {
        super(id);
    }

    public Iterable<IDomainEvent> domainEvents() {
        return List.copyOf(domainEvents);
    }

    public void raise(IDomainEvent domainEvent) {
        domainEvents.add(domainEvent);
    }
}
