package com.bytemonk.securityincidents.abstractions.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class Entity {
    protected Long id;

    public Entity(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Entity otherEntity = (Entity) other;
        return Objects.equals(this.id, otherEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
