package com.petrovdevelopment.paytmcurrencyconverter.platform.services.gateways;

import com.petrovdevelopment.paytmcurrencyconverter.domain.gateways.EntityGateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.JsonParser;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.models.ExchangeRatesResponse;


import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Andrey on 2017-12-18.
 */

public class WebEntityGateway implements EntityGateway {
    OkHttpClient client;

    public WebEntityGateway() {
        client = new OkHttpClient(); //TODO push okhttp client one lavel deeper
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
        Request request = new Request.Builder().url("https://api.fixer.io/latest?base=" + currency).build();
        Response response = client.newCall(request).execute();
        return response;
    }

}



