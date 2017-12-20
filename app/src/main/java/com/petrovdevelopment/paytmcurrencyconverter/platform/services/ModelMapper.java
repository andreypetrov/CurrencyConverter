package com.petrovdevelopment.paytmcurrencyconverter.platform.services;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.models.ExchangeRatesResponse;

import java.lang.reflect.Type;

/**
 * Created by Andrey on 2017-12-20.
 */

public class ModelMapper {
    static Gson gson = new Gson();

    public static ExchangeRatesResponse transformExchangeRatesResponse(String exchangeRatesResponseBody) throws JsonSyntaxException {
        final Type exchangeRatesResponseType = new TypeToken<ExchangeRatesResponse>() {}.getType();
        return gson.fromJson(exchangeRatesResponseBody, exchangeRatesResponseType);
    }

}
