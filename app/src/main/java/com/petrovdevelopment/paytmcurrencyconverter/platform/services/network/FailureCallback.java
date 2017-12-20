package com.petrovdevelopment.paytmcurrencyconverter.platform.services.network;

/**
 * Created by Andrey on 2017-12-18.
 */

public interface FailureCallback<T> {
    void onFailure(T model);
}
