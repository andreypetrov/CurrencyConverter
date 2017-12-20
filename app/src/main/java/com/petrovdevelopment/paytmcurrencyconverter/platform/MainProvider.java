package com.petrovdevelopment.paytmcurrencyconverter.platform;

import com.petrovdevelopment.paytmcurrencyconverter.platform.services.gateways.XmlLocalGateway;

/**
 * Created by Andrey on 2017-12-19.
 */

public interface MainProvider {
    public XmlLocalGateway getLocalGateway();
}
