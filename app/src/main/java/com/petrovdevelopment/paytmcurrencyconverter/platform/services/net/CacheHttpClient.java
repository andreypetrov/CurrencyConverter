package com.petrovdevelopment.paytmcurrencyconverter.platform.services.net;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Andrey on 2017-12-21.
 * Coding is fun!
 */

public class CacheHttpClient implements HttpClient {
    private static final String DIRECTORY_NAME = "http_cache";

    //headers
    private static final String CONTENT_TYPE_NAME = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";

    private static final String CACHE_CONTROL_NAME = "Cache-Control";
    private static final String CACHE_CONTROL_VALUE_PREFIX = "public, max-age=";

    private static final int DEFAULT_CACHE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final int DEFAULT_CACHE_AGE_IN_SECONDS = 30*60; // 30 minutes

    private final OkHttpClient okHttpClient;

    public CacheHttpClient(Context context, int cacheSize, int cacheAgeInMinutes) {
        okHttpClient = createClient(context, cacheSize, cacheAgeInMinutes);
    }

    /**
     * Factory method.
     * Create a default http client with cache size of 5MB and stale cache age of 30 min
     * @param context required to open a cache File
     * @return http client supporting cache
     */
    public static CacheHttpClient createDefaultClient(Context context) {
        return new CacheHttpClient(context, DEFAULT_CACHE_SIZE, DEFAULT_CACHE_AGE_IN_SECONDS);
    }

    private static OkHttpClient createClient(Context context, int size, int cacheAgeInMinutes) {
       return new OkHttpClient.Builder()
                .cache(createHttpClientCache(context, size))
                .addNetworkInterceptor(createCacheInterceptor(cacheAgeInMinutes))
                .build();
    }

    private static Interceptor createCacheInterceptor(int cacheAgeInMinutes) {
        return chain -> chain.proceed(chain.request())
                .newBuilder()
                .header(CACHE_CONTROL_NAME, CACHE_CONTROL_VALUE_PREFIX + cacheAgeInMinutes)
                .build();
    }

    private static Cache createHttpClientCache(Context context, int size) {
        File cacheDir = context.getDir(DIRECTORY_NAME, Context.MODE_PRIVATE);
        return new Cache(cacheDir, size);
    }

    @Override
    public Response get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader(CONTENT_TYPE_NAME, CONTENT_TYPE_VALUE_JSON)
                .build();
        return okHttpClient.newCall(request).execute();
    }
}
