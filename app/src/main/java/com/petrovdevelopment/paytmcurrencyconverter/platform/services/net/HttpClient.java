package com.petrovdevelopment.paytmcurrencyconverter.platform.services.net;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by Andrey on 2017-12-21.
 */

public interface HttpClient {

    /**
     * Synchronous network operation. Call only on background thread!
     * @param url
     * @return
     * @throws IOException
     */
    Response get(String url) throws IOException;
}
