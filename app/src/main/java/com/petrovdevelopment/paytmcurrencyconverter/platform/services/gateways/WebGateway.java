package com.petrovdevelopment.paytmcurrencyconverter.platform.services.gateways;

import android.content.Context;

import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.AsynchronousGateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.JsonParser;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.models.ExchangeRatesResponse;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.net.CacheHttpClient;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.net.HttpClient;


import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Andrey on 2017-12-18.
 */

public class WebGateway implements AsynchronousGateway {
    HttpClient client;

    public WebGateway(HttpClient client) {
        this.client = client;
    }

    @Override
    public Observable<ExchangeRatesResponse> fetchExchangeRates(String currency) {
        Observable<ExchangeRatesResponse> observable = Observable
                .fromCallable(() -> fetchExchangeRatesFromWeb(currency))
                .subscribeOn(Schedulers.io())
                .map(response -> response.body().string())
                .map(bodyString -> JsonParser.transformExchangeRatesResponse(bodyString));
        return observable;

//
//        return Observable.create(emitter -> {
//            try {
//                String exchangeRatesResponseJson = fetchExchangeRatesFromWeb(currency);
//                if (exchangeRatesResponseJson != null) {
//                    ExchangeRatesResponse exchangeRatesResponse = JsonParser.transformExchangeRatesResponse(exchangeRatesResponseJson);
//                    emitter.onNext(exchangeRatesResponse);
//                    emitter.onComplete();
//                } else {
//                    emitter.onError(new NetworkConnectionException("No response body"));
//                }
//            } catch (Exception e) {
//                emitter.onError(new NetworkConnectionException(e.getCause()));
//            }
//        });
    }

    //TODO add parameter validation in a validator file - should check if it is only alphabetic characters in [A-Z]. No need to check for length probably
    private Response fetchExchangeRatesFromWeb(String currency) throws IOException {
        Response response = client.get("https://api.fixer.io/latest?base=" + currency);
        return response;
    }

}



