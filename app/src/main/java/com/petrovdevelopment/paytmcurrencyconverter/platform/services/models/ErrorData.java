package com.petrovdevelopment.paytmcurrencyconverter.platform.services.models;

/**
 * Created by Andrey on 2017-12-20.
 */

public class ErrorData {
    private String message;
    private String errorCode;

    public ErrorData(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
