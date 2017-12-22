package com.petrovdevelopment.paytmcurrencyconverter.domain.usecases;

import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.SynchronousGateway;
import com.petrovdevelopment.paytmcurrencyconverter.domain.models.Currency;

import java.util.List;

/**
 * Created by Andrey on 2017-12-18.
 * Coding is fun!
 */

public class LocalCurrenciesUseCase implements SynchronousUseCase<List<Currency>> {
    private final SynchronousGateway synchronousGateway;

    LocalCurrenciesUseCase(SynchronousGateway synchronousGateway) {
        this.synchronousGateway = synchronousGateway;
    }

    public List<Currency> execute() {
        return synchronousGateway.getCurrencies();
    }
}
