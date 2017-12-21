package com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways;

import com.petrovdevelopment.paytmcurrencyconverter.presentation.viewmodels.Currency;

import java.util.List;

/**
 * Created by Andrey on 2017-12-20.
 */

public interface LocalGateway {

    /**
     *  Synchronous fetch
     * @return
     */
    List<Currency> getCurrencies();
}
