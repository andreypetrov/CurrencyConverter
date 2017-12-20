package com.petrovdevelopment.paytmcurrencyconverter.domain.usecases;

import com.petrovdevelopment.paytmcurrencyconverter.domain.gateways.Gateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.models.ExchangeRatesResponse;
import com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels.CurrencyVM;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Andrey on 2017-12-20.
 */

public class ExchangeRatesUseCase implements AsynchronousUseCase<ExchangeRatesResponse>{
    Gateway gateway;
    String currency;

    public ExchangeRatesUseCase(Gateway gateway, String currency) {
        this.gateway = gateway;
        this.currency = currency;
    }

    @Override
    public Observable<ExchangeRatesResponse> execute() {
        return gateway.fetchExchangeRates(currency);
    }
}
