package com.petrovdevelopment.paytmcurrencyconverter.domain.usecases;

/**
 * Created by Andrey on 2017-12-20.
 */

public interface SynchronousUseCase<T> {
    T execute();
}
