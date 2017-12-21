package com.petrovdevelopment.paytmcurrencyconverter.platform.services.net;

import okhttp3.Response;

/**
 * Created by Andrey on 2017-12-21.
 */

public interface HttpClient {
    Response get(String url);
}
