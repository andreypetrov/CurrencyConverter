package com.petrovdevelopment.paytmcurrencyconverter.domain.usecases;

import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.AsynchronousGateway;
import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.SynchronousGateway;

/**
 * Created by Andrey on 2017-12-21.
 * Coding is fun!
 */

public class DomainUseCaseFactory {
    private  AsynchronousGateway asynchronousGateway;
    private SynchronousGateway synchronousGateway;

    public DomainUseCaseFactory(AsynchronousGateway asynchronousGateway, SynchronousGateway synchronousGateway) {
        this.asynchronousGateway = asynchronousGateway;
        this.synchronousGateway = synchronousGateway;
    }

    public ExchangeRatesUseCase exchangeRatesUseCase(String currency) {
        return new ExchangeRatesUseCase(asynchronousGateway, currency);
    }

    public LocalCurrenciesUseCase localCurrenciesUseCase() {
        return new LocalCurrenciesUseCase(synchronousGateway);
    }
}
