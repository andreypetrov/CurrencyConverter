package com.petrovdevelopment.paytmcurrencyconverter.platform.services.di;

import android.app.Application;

import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.EntityGateway;
import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.LocalGateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.gateways.WebEntityGateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.gateways.XmlLocalGateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.utils.Log;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.di.MainProvider;

/**
 * Created by Andrey on 2017-12-18.
 */

public class MainApplication extends Application implements MainProvider {
    LocalGateway localGateway;
    EntityGateway entityGateway;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.log(this, "hello");
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
