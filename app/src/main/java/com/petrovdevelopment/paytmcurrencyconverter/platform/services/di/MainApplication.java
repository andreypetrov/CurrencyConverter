package com.petrovdevelopment.paytmcurrencyconverter.platform.services.di;

import android.app.Application;

import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.AsynchronousGateway;
import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.SynchronousGateway;
import com.petrovdevelopment.paytmcurrencyconverter.domain.usecases.DomainUseCaseFactory;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.gateways.WebGateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.gateways.XmlLocalGateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.net.CacheHttpClient;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.net.HttpClient;
import com.petrovdevelopment.paytmcurrencyconverter.platform.utils.Log;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.di.MainProvider;

/**
 * Created by Andrey on 2017-12-18.
 * Coding is fun!
 */

public class MainApplication extends Application implements MainProvider {
    private SynchronousGateway localGateway;
    private AsynchronousGateway asynchronousGateway;
    private DomainUseCaseFactory domainUseCaseFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.log(this, "hello");
    }

    private SynchronousGateway getSynchronousGateway() {
        if (localGateway == null) localGateway = new XmlLocalGateway(this);
        return localGateway;
    }

    private AsynchronousGateway getAsynchronousGateway() {
        if (asynchronousGateway == null) {
            HttpClient client = CacheHttpClient.createDefaultClient(this);
            asynchronousGateway = new WebGateway(client);
        }
        return asynchronousGateway;
    }

    @Override
    public DomainUseCaseFactory getDomainUseCaseFactory() {
        if (domainUseCaseFactory == null) {
            domainUseCaseFactory = new DomainUseCaseFactory(getAsynchronousGateway(), getSynchronousGateway());
        }
        return domainUseCaseFactory;
    }

}
