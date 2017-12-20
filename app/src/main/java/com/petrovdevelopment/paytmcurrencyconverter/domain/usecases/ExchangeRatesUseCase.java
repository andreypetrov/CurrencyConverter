package com.petrovdevelopment.paytmcurrencyconverter.domain.usecases;

import com.petrovdevelopment.paytmcurrencyconverter.domain.gateways.EntityGateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.models.ExchangeRatesResponse;

import io.reactivex.Observable;

/**
 * Created by Andrey on 2017-12-20.
 */

public class ExchangeRatesUseCase implements AsynchronousUseCase<ExchangeRatesResponse>{
    EntityGateway entityGateway;
    String currency;

    public ExchangeRatesUseCase(EntityGateway entityGateway, String currency) {
        this.entityGateway = entityGateway;
        this.currency = currency;
    }

    @Override
    public Observable<ExchangeRatesResponse> execute() {
        return entityGateway.fetchExchangeRates(currency);
    }
}
