package com.petrovdevelopment.paytmcurrencyconverter.platform.services;

/**
 * Created by Andrey on 2017-12-20.
 */

public interface OnResponseSuccessCallback<T> {
    void onSuccess(T response);
}
