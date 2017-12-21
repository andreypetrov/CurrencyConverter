package com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways;

import com.petrovdevelopment.paytmcurrencyconverter.platform.services.models.ExchangeRatesResponse;

import io.reactivex.Observable;

/**
 * Created by Andrey on 2017-12-20.
 */

public interface EntityGateway {
    Observable<ExchangeRatesResponse> fetchExchangeRates(String currency);
}
