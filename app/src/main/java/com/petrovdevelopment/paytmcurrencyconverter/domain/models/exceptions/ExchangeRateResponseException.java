package com.petrovdevelopment.paytmcurrencyconverter.domain.models.exceptions;

/**
 * Created by Andrey on 2017-12-21.
 * Coding is fun!
 */

public class ExchangeRateResponseException extends Error {
    public ExchangeRateResponseException(String message) {
        super(message);
    }
}
