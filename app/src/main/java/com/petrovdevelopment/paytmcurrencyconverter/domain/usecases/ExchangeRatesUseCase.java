package com.petrovdevelopment.paytmcurrencyconverter.domain.usecases;

import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.AsynchronousGateway;
import com.petrovdevelopment.paytmcurrencyconverter.domain.models.ExchangeRatesResponse;

import io.reactivex.Observable;

/**
 * Created by Andrey on 2017-12-20.
 * Coding is fun!
 */

public class ExchangeRatesUseCase implements AsynchronousUseCase<ExchangeRatesResponse>{
    private final AsynchronousGateway asynchronousGateway;
    private final String currency;

    ExchangeRatesUseCase(AsynchronousGateway asynchronousGateway, String currency) {
        this.asynchronousGateway = asynchronousGateway;
        this.currency = currency;
    }

    @Override
    public Observable<ExchangeRatesResponse> execute() {
        return asynchronousGateway.fetchExchangeRates(currency);
    }
}
