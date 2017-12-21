package com.petrovdevelopment.paytmcurrencyconverter.domain.usecases;

import io.reactivex.Observable;

/**
 * TODO create use case factories
 * Created by Andrey on 2017-12-20.
 */

public interface AsynchronousUseCase<T> {
    Observable<T>  execute();
}
