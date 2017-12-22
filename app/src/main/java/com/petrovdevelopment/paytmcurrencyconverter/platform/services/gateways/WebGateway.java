package com.petrovdevelopment.paytmcurrencyconverter.platform.services.gateways;

import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.AsynchronousGateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.JsonParser;
import com.petrovdevelopment.paytmcurrencyconverter.domain.models.ExchangeRatesResponse;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.net.HttpClient;


import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Response;

/**
 * Created by Andrey on 2017-12-18.
 * Coding is fun!
 */

public class WebGateway implements AsynchronousGateway {
    private final HttpClient client;

    public WebGateway(HttpClient client) {
        this.client = client;
    }

    @Override
    public Observable<ExchangeRatesResponse> fetchExchangeRates(String currency)  {
        return Observable
                .fromCallable(() -> fetchExchangeRatesFromWeb(currency))
                .subscribeOn(Schedulers.io())
                .map(response -> response.body().string())
                .map(JsonParser::transformExchangeRatesResponse);
    }

    //TODO add parameter validation in a validator file - should check if it is only alphabetic characters in [A-Z]. No need to check for length probably
    private Response fetchExchangeRatesFromWeb(String currency) throws IOException {
        return client.get("https://api.fixer.io/latest?base=" + currency);
    }


}



