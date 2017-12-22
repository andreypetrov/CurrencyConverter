package com.petrovdevelopment.paytmcurrencyconverter.platform.services.net;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by Andrey on 2017-12-21.
 * Coding is fun!
 */

public interface HttpClient {

    /**
     * Synchronous network operation. Call only on background thread!
     * @param url get rest resource with given url
     * @return okhttp response
     * @throws IOException during okhttp usage
     */
    Response get(String url) throws IOException;
}
