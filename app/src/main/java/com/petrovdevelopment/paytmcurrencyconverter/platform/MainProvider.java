package com.petrovdevelopment.paytmcurrencyconverter.platform;

import com.petrovdevelopment.paytmcurrencyconverter.platform.services.LocalGateway;

/**
 * Created by Andrey on 2017-12-19.
 */

public interface MainProvider {
    public LocalGateway getLocalGateway();
}
