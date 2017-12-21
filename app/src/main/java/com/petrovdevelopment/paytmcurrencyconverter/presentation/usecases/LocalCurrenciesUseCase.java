package com.petrovdevelopment.paytmcurrencyconverter.presentation.usecases;

import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.SynchronousGateway;
import com.petrovdevelopment.paytmcurrencyconverter.domain.usecases.SynchronousUseCase;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.viewmodels.Currency;

import java.util.List;

/**
 * Created by Andrey on 2017-12-18.
 */

public class LocalCurrenciesUseCase implements SynchronousUseCase<List<Currency>> {
    private final SynchronousGateway localGateway;

    public LocalCurrenciesUseCase(SynchronousGateway localGateway) {
        this.localGateway = localGateway;
    }

    public List<Currency> execute() {
        return localGateway.getCurrencies();
    }
}
