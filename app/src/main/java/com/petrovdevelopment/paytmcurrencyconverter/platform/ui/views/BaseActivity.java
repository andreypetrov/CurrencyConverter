package com.petrovdevelopment.paytmcurrencyconverter.platform.ui.views;

import android.app.Activity;

import com.petrovdevelopment.paytmcurrencyconverter.platform.services.di.MainApplication;

/**
 * Created by Andrey on 2017-12-19.
 */

public class BaseActivity extends Activity {

    /**
     * Convenience method to access the application object without casting every time
     *
     * @return
     */
    public MainApplication getApp() {
        return (MainApplication) getApplication();
    }

}
