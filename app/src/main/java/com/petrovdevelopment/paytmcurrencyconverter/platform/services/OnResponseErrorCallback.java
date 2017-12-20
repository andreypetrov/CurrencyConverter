package com.petrovdevelopment.paytmcurrencyconverter.platform.services;

import com.petrovdevelopment.paytmcurrencyconverter.platform.services.models.ErrorData;

/**
 * Created by Andrey on 2017-12-20.
 */

public interface OnResponseErrorCallback {
    void onError(ErrorData error);
}
