package com.petrovdevelopment.paytmcurrencyconverter.domain.gateways;

import com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels.CurrencyVM;

import java.util.List;

/**
 * Created by Andrey on 2017-12-20.
 */

public interface LocalGateway {

    /**
     *  Synchronous fetch
     * @return
     */
    List<CurrencyVM> getCurrencies();
}
