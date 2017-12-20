package com.petrovdevelopment.paytmcurrencyconverter.platform.services.network;

/**
 * Created by Andrey on 2017-12-18.
 */

interface SuccessCallback<T> {
    void onSuccess(T model);
}
