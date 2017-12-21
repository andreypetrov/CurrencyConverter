package com.petrovdevelopment.paytmcurrencyconverter.platform.services.net;

import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by Andrey on 2017-12-21.
 */

public class CachedHttpClient implements  HttpClient{

    private OkHttpClient okHttpClient;

    public CachedHttpClient(int cacheMaxStaleAge) {
        okHttpClient = new OkHttpClient();

    }

    OkHttpClient getClient() {
        return okHttpClient;
    }

    @Override
    public Response get(String url) {
        return null;
    }
}
