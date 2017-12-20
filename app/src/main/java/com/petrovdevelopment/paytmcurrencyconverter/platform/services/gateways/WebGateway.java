package com.petrovdevelopment.paytmcurrencyconverter.platform.services.gateways;

import com.petrovdevelopment.paytmcurrencyconverter.domain.gateways.Gateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.models.ExchangeRatesResponse;


import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Andrey on 2017-12-18.
 */

public class WebGateway implements Gateway {
    OkHttpClient okHttpClient;

    public WebGateway() {
        okHttpClient = new OkHttpClient();
    }

    public Observable<ExchangeRatesResponse> fetchExchangeRates(String currency) {
        return null;
//        return Observable.create(emitter -> {
//                try {
//                    String exchangeRates = getUserEntitiesFromApi();
//                    if (responseUserEntities != null) {
//                        emitter.onNext(userEntityJsonMapper.transformUserEntityCollection(
//                                responseUserEntities));
//                        emitter.onComplete();
//                    } else {
//                        emitter.onError(new NetworkConnectionException());
//                    }
//                } catch (Exception e) {
//                    emitter.onError(new NetworkConnectionException(e.getCause()));
//                }
//            } else {
//                emitter.onError(new NetworkConnectionException());
//            }
//        });
//
//
//
//
//
//
//
//        Request request = new Request.Builder().url("https://api.fixer.io/latest").build();
//        Observable<String> r = Observable
//                    .fromCallable(()-> client.newCall(request).execute())
//                    .subscribeOn(Schedulers.io())
//                    .map(response -> response.body().string())
//                    .observeOn(AndroidSchedulers.mainThread());


            //shimmerContainer.startShimmerAnimation();

            //todo add spinner
//            Observable<String> r = Observable
//                    .fromCallable(()-> client.newCall(request).execute())
//                    .subscribeOn(Schedulers.io())
//                    .map(response -> response.body().string())
//                    .observeOn(AndroidSchedulers.mainThread());

            //problem is the lifecycle of this observer
//            DisposableObserver<String> observer;
//            observer = createObserver(onResponseErrorCallback, onResponseSuccessCallback);
//            r.subscribe(observer);


    }


//
//    DisposableObserver<String> createObserver(OnResponseErrorCallback onResponseErrorCallback, OnResponseSuccessCallback<ExchangeRatesResponse> onResponseSuccessCallback) {
//        return new DisposableObserver<String>() {
//            @Override
//            public void onNext(String s) {
//                onResponseSuccessCallback.onSuccess( new ExchangeRatesResponse());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                onResponseErrorCallback.onError(new ErrorData(e.getLocalizedMessage(), e.getMessage()));/
//            }
//
//            @Override
//            public void onComplete() {
//                //shimmerContainer.stopShimmerAnimation();
////                progressBar.setIndeterminate(false);
////                progressBar.setVisibility(View.GONE);
//                //todo remove spinner
//                L.log(this, "network call completed");
//            }
//        };
//    }
}
