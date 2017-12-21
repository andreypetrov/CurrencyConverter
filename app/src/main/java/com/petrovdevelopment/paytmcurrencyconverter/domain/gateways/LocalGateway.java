package com.petrovdevelopment.paytmcurrencyconverter.domain.gateways;

import com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels.Currency;

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
