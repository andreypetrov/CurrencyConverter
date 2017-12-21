package com.petrovdevelopment.paytmcurrencyconverter.platform.services.models.exceptions;

/**
 * Created by Andrey on 2017-12-21.
 */

public class ExchangeRateResponseException extends Error {
    public ExchangeRateResponseException(String message) {
        super(message);
    }
}
