package com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways;

import com.petrovdevelopment.paytmcurrencyconverter.domain.models.Currency;

import java.util.List;

/**
 * Created by Andrey on 2017-12-20.
 */

public interface SynchronousGateway {
    List<Currency> getCurrencies();
}
