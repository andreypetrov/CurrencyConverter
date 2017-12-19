package com.petrovdevelopment.paytmcurrencyconverter.platform;

import android.app.Application;

import com.petrovdevelopment.paytmcurrencyconverter.platform.utilities.L;

/**
 * Created by Andrey on 2017-12-18.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        L.log(this, "hello");
    }
}
