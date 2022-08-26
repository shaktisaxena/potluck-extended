package com.shakti.potluck.controller;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ServiceResponse<T> {

    private HttpStatus responseCode;
    private MsgServiceResponse responseMessage;
    private T responseObject;

    public ServiceResponse(HttpStatus code, MsgServiceResponse msg) {
        this.responseCode = code;
        this.responseMessage = msg;
    }

    public void setResponseCode(HttpStatus responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseMessage(MsgServiceResponse responseMessage) {
        this.responseMessage = responseMessage;
    }
    public T getResponseObject() {
        return responseObject;
    }
    public void setResponseObject(T responseObject) {
        this.responseObject = responseObject;
    }

    public void setSuccessResponse() {
        this.setResponseCode(HttpStatus.OK);
        this.setResponseMessage(MsgServiceResponse.OK);
    }

    public void setInternalServerErrorResponse() {
        this.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);
        this.setResponseMessage(MsgServiceResponse.TRANSACTION_PROBLEM);
    }
}

