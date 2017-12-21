package com.petrovdevelopment.paytmcurrencyconverter.presentation.presenterusecases;

import com.petrovdevelopment.paytmcurrencyconverter.domain.gateways.LocalGateway;
import com.petrovdevelopment.paytmcurrencyconverter.domain.usecases.SynchronousUseCase;
import com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels.Currency;

import java.util.List;

/**
 * Created by Andrey on 2017-12-18.
 */

public class LocalCurrenciesUseCase implements SynchronousUseCase<List<Currency>> {
    private final LocalGateway localGateway;

    public LocalCurrenciesUseCase(LocalGateway localGateway) {
        this.localGateway = localGateway;
    }

    public List<Currency> execute() {
        return localGateway.getCurrencies();
    }
}
