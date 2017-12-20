package com.petrovdevelopment.paytmcurrencyconverter.platform;

import android.app.Application;

import com.petrovdevelopment.paytmcurrencyconverter.domain.gateways.EntityGateway;
import com.petrovdevelopment.paytmcurrencyconverter.domain.gateways.LocalGateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.gateways.WebEntityGateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.gateways.XmlLocalGateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.utilities.L;

/**
 * Created by Andrey on 2017-12-18.
 */

public class MainApplication extends Application implements MainProvider {
    LocalGateway localGateway;
    EntityGateway entityGateway;
    @Override
    public void onCreate() {
        super.onCreate();
        L.log(this, "hello");
    }

    @Override
    public LocalGateway getLocalGateway() {
        if (localGateway == null) localGateway = new XmlLocalGateway(this);
        return localGateway;
    }

    @Override
    public EntityGateway getEntityGateway() {
        if (entityGateway == null) entityGateway = new WebEntityGateway();
        return entityGateway;
    }
}
