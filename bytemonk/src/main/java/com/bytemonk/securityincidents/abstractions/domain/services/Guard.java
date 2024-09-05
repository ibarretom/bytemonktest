package com.bytemonk.securityincidents.abstractions.domain.services;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;

import java.text.MessageFormat;

public class Guard {
    public static class Against {
        public static String NullOrEmpty(String aValue) throws DomainException {
            if(aValue == null || aValue.trim().isEmpty())
                throw new DomainException("You must provide a non-nullable/non-empty value for property.");

            return aValue;
        }
        
        public static String SmallerThen(int aLength, String aValue) throws DomainException {
            NullOrEmpty(aValue);

            if (aLength > aValue.length()) {
                String aMessage = "Value {0} must be bigger then {1} characters";
                throw new DomainException(MessageFormat.format(aMessage, aValue, aLength));
            }

            return aValue;
        }

       
    }
}
