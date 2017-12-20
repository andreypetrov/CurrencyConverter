package com.petrovdevelopment.paytmcurrencyconverter.platform;

import android.app.Application;

import com.petrovdevelopment.paytmcurrencyconverter.platform.services.LocalGateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.utilities.L;

/**
 * Created by Andrey on 2017-12-18.
 */

public class MainApplication extends Application implements MainProvider {
    LocalGateway localGateway;

    @Override
    public void onCreate() {
        super.onCreate();
        L.log(this, "hello");
    }

    public LocalGateway getLocalGateway() {
        if (localGateway == null) localGateway = new LocalGateway(this);
        return localGateway;
    }
}
