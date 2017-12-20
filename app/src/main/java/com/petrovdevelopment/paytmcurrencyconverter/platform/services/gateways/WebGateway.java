package com.petrovdevelopment.paytmcurrencyconverter.platform.services.gateways;

import android.view.View;

import com.petrovdevelopment.paytmcurrencyconverter.platform.services.OnResponseErrorCallback;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.OnResponseSuccessCallback;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.models.ErrorData;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.models.ExchangeRatesReponse;
import com.petrovdevelopment.paytmcurrencyconverter.platform.utilities.L;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Andrey on 2017-12-18.
 */

public class WebGateway implements Gateway {

    public void fetchExchangeRates(String currency, OnResponseErrorCallback onResponseErrorCallback, OnResponseSuccessCallback<ExchangeRatesReponse> onResponseSuccessCallback) {
            //TODO implement call via rxjava, calling the callbacks back on the main thread

            ExchangeRatesReponse response = new ExchangeRatesReponse();
            onResponseSuccessCallback.onSuccess(response);

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("https://api.fixer.io/latest").build();

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
//    DisposableObserver<String> createObserver(OnResponseErrorCallback onResponseErrorCallback, OnResponseSuccessCallback<ExchangeRatesReponse> onResponseSuccessCallback) {
//        return new DisposableObserver<String>() {
//            @Override
//            public void onNext(String s) {
//                onResponseSuccessCallback.onSuccess( new ExchangeRatesReponse());
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
