package com.petrovdevelopment.paytmcurrencyconverter.domain.usecases;

import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.AsynchronousGateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.models.ExchangeRatesResponse;

import io.reactivex.Observable;

/**
 * Created by Andrey on 2017-12-20.
 */

public class ExchangeRatesUseCase implements AsynchronousUseCase<ExchangeRatesResponse>{
    private AsynchronousGateway asynchronousGateway;
    private String currency;

    public ExchangeRatesUseCase(AsynchronousGateway asynchronousGateway, String currency) {
        this.asynchronousGateway = asynchronousGateway;
        this.currency = currency;
    }

    @Override
    public Observable<ExchangeRatesResponse> execute() {
        return asynchronousGateway.fetchExchangeRates(currency);
    }
}
