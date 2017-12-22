package com.petrovdevelopment.paytmcurrencyconverter.presentation.observers;

import io.reactivex.observers.DisposableObserver;

/**
 * Provides default implementation for the cases in which we don't need to implement all methods
 * Created by Andrey on 2017-12-20.
 */
public class BaseObserver<T> extends DisposableObserver<T> {
    @Override public void onNext(T t) {
    }

    @Override public void onComplete() {
    }

    @Override public void onError(Throwable exception) {
    }
}