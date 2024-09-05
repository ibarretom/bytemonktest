package com.bytemonk.securityincidents.abstractions.application.services;

import com.bytemonk.securityincidents.abstractions.application.domain.valueobjects.ApplicationResponse;
import com.bytemonk.securityincidents.abstractions.application.domain.valueobjects.EOperationCode;
import com.bytemonk.securityincidents.abstractions.application.domain.valueobjects.UseCaseRequest;
import com.bytemonk.securityincidents.abstractions.domain.exceptions.DomainException;
import com.bytemonk.securityincidents.abstractions.domain.exceptions.ValidationException;

public abstract class UseCaseBase<TRequest extends UseCaseRequest, TResponse> {
    public ApplicationResponse<TResponse> execute(TRequest aRequest, EOperationCode aSuccessCode) {
        try {
            var response = process(aRequest);

            return ApplicationResponse.success(response, aSuccessCode);
        }catch(DomainException ex) {
            return ApplicationResponse.error("We run into a problem. Internal error", EOperationCode.INTERNAL_ERROR);
        } catch(ValidationException ex) {
            return ApplicationResponse.error(ex.getMessage(), EOperationCode.USER_INPUT_ERROR);
        }
    }

    protected abstract TResponse process(TRequest aRequest) throws DomainException, ValidationException;
}
