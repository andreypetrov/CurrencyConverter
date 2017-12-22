package com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.di;

import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.AsynchronousGateway;
import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.SynchronousGateway;
import com.petrovdevelopment.paytmcurrencyconverter.domain.usecases.DomainUseCaseFactory;

/**
 * Created by Andrey on 2017-12-19.
 * Coding is fun!
 */

public interface MainProvider {
    DomainUseCaseFactory getDomainUseCaseFactory();
}
