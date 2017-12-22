package com.petrovdevelopment.paytmcurrencyconverter.domain.usecases;

import com.petrovdevelopment.paytmcurrencyconverter.domain.models.Currency;
import com.petrovdevelopment.paytmcurrencyconverter.domain.models.ExchangeRatesResponse;
import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.AsynchronousGateway;
import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.SynchronousGateway;

import java.util.List;

/**
 * Created by Andrey on 2017-12-21.
 * Coding is fun!
 */

public class DomainUseCaseFactory {
    private  final AsynchronousGateway asynchronousGateway;
    private final SynchronousGateway synchronousGateway;

    public DomainUseCaseFactory(AsynchronousGateway asynchronousGateway, SynchronousGateway synchronousGateway) {
        this.asynchronousGateway = asynchronousGateway;
        this.synchronousGateway = synchronousGateway;
    }

    public AsynchronousUseCase<ExchangeRatesResponse> exchangeRatesUseCase(String currency) {
        return new ExchangeRatesUseCase(asynchronousGateway, currency);
    }

    public SynchronousUseCase<List<Currency>> localCurrenciesUseCase() {
        return new LocalCurrenciesUseCase(synchronousGateway);
    }
}
