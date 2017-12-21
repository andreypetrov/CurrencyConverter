package com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.di;

import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.EntityGateway;
import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.LocalGateway;

/**
 * Created by Andrey on 2017-12-19.
 */

public interface MainProvider {
    public LocalGateway getLocalGateway();
    public EntityGateway getEntityGateway();

}
