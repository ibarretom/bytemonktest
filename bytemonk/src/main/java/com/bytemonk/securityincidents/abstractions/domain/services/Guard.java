package com.bytemonk.securityincidents.abstractions.domain.services;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.abstractions.domain.exceptions.ValidationException;
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

    public static class Validate {
        public static String NullOrEmpty(String aValue) throws ValidationException {
           try {
               return Guard.Against.NullOrEmpty(aValue);
           }catch(DomainException ex) {
                throw new ValidationException("Value cannot be null or empty");
           }
        }

        public static String SmallerThen(int aLength, String aValue) throws ValidationException {
            try {
                return Guard.Against.SmallerThen(aLength, aValue);
            }catch(DomainException ex) {
                throw new ValidationException("Value cannot be smaller then 100 characters");
            }
        }

        public static <T extends Enum<T>> String UnBounded(String aValue, Class<T> enumType) {
            try {
                Enum.valueOf(enumType, aValue);

                return aValue;
            }catch (IllegalArgumentException ex) {
                throw new ValidationException("The provided value must a valid ");
            }
        }

        public static Date OutsideLimit(Date aDate, DateRange aDateRange) throws ValidationException {
            try {
                return Guard.Against.OutsideLimit(aDate, aDateRange);
            }catch (DomainException ex) {
                throw new ValidationException(MessageFormat.format("Date must be between {0} and {1}", aDateRange.start().toString(), aDateRange.end().toString()));
            }
        }
    }
}
