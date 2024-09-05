package com.bytemonk.securityincidents.abstractions.domain.services;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.abstractions.valueobjects.DateRange;

import java.text.MessageFormat;
import java.util.Date;

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

        public static Date NullOrEmptyDate(Date aDate) throws DomainException {
            if(aDate == null || aDate.equals(DateFactory.empty()))
                throw new DomainException("You must provide a non-nullable/non-empty date.");

            return aDate;
        }

        public static Date OutsideLimit(Date aDate, DateRange aDateRange) throws DomainException {
            if (!aDateRange.includes(aDate)) {
                String aMessage = "You provided a date that is earlier than the allowed limit of: {0}.";
                throw new DomainException(MessageFormat.format(aMessage, aDateRange.getDays()));
            }
            return aDate;
        }
    }
}
