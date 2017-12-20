package com.petrovdevelopment.paytmcurrencyconverter.platform;

import com.petrovdevelopment.paytmcurrencyconverter.domain.gateways.EntityGateway;
import com.petrovdevelopment.paytmcurrencyconverter.domain.gateways.LocalGateway;

/**
 * Created by Andrey on 2017-12-19.
 */

public interface MainProvider {
    public LocalGateway getLocalGateway();
    public EntityGateway getEntityGateway();

}
