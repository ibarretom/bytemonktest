package com.bytemonk.securityincidents.abstractions.adapters.presentation.domain;

import com.bytemonk.securityincidents.abstractions.application.domain.valueobjects.ApplicationResponse;

import java.util.ArrayList;

public class ControllerResponse<T> {
    Iterable<T> Response;
    String Message;

    private ControllerResponse(Iterable<T> Response) {
        this.Response = Response;
        this.Message = "";
    }

    private ControllerResponse(String Message) {
        this.Message = Message;
        this.Response = new ArrayList<T>();
    }

    public static <TResponse> ControllerResponse<TResponse> create(ApplicationResponse<TResponse> anApplicationResponse) {
        if (!anApplicationResponse.isSuccess())
            return new ControllerResponse<TResponse>(anApplicationResponse.getMessage());

        var aResponseList = new ArrayList<TResponse>();
        aResponseList.add(anApplicationResponse.getResult());

        return new ControllerResponse<TResponse>(aResponseList);
    }

}
