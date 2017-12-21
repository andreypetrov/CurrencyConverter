package com.petrovdevelopment.paytmcurrencyconverter.platform.services.net;

import okhttp3.OkHttpClient;

/**
 * Created by Andrey on 2017-12-21.
 */

public class HttpClient {


    OkHttpClient okHttpClient;

    public HttpClient(int cacheMaxStaleAge) {

        okHttpClient = new OkHttpClient();
    }


    

}
