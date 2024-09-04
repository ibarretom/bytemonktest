package com.bytemonk.securityincidents.abstractions.domain.services;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;

public class Guard {
    public static class Against {
        public static String NullOrEmpty(String aValue) throws DomainException {
            if(aValue == null || aValue.trim().isEmpty())
                throw new DomainException("You must provide a non-nullable/non-empty value for property.");

            return aValue;
        }
    }
}
