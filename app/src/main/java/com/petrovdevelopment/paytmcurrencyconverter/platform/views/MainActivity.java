package com.petrovdevelopment.paytmcurrencyconverter.platform.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.petrovdevelopment.paytmcurrencyconverter.R;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.LocalGateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.utilities.L;
import com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels.CurrencyVM;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.MainPresenter;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.MainView;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity implements MainView{
    TextView testTextView;
    MainPresenter presenter;
    DisposableObserver<String> observer;
    //ShimmerFrameLayout shimmerContainer;
    ProgressBar progressBar;
    RecyclerView currenciesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testTextView = (TextView) findViewById(R.id.testTextView);
        //shimmerContainer = (ShimmerFrameLayout) findViewById(R.id.shimmerContainer);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        currenciesRecyclerView  = (RecyclerView) findViewById(R.id.currenciesRecyclerView);
        testTextView.setText("test");
        presenter = new MainPresenter(); //TODO replace this with injection

        //test
        configureRecylcerView();
    }

    private void configureRecylcerView() {
        // use a linear layout manager
        LinearLayoutManager layoutManger = new LinearLayoutManager(this);
        currenciesRecyclerView.setLayoutManager(layoutManger);
        List<CurrencyVM> currencyVMList = LocalGateway.getCurrencies(this);

        RecyclerView.Adapter adapter = new CurrenciesAdapter(currencyVMList);
        currenciesRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        testRxJava();
        presenter.viewReady();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    DisposableObserver<String> createObserver() {
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                update(s);
            }

            @Override
            public void onError(Throwable e) {
                update(e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                //shimmerContainer.stopShimmerAnimation();
                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.GONE);
                //todo remove spinner
                L.log(this, "network call completed");
            }
        };
    }

    //TODO move all that logic deeper into the clean architecture stack
    private void testRxJava() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.fixer.io/latest").build();

        //shimmerContainer.startShimmerAnimation();
        progressBar.setIndeterminate(true);
        //todo add spinner
        Observable<String> r = Observable
                .fromCallable(()-> client.newCall(request).execute())
                .subscribeOn(Schedulers.io())
                .map(response -> response.body().string())
                .observeOn(AndroidSchedulers.mainThread());
        observer = createObserver();
        r.subscribe(observer);

    }

    public void addSpinner() {
        //add local spinner only in the recycler view
    }

    public void removeSpinner() {
        //remove local spinner only in the recycler view
    }


    public void update(String s) {
        L.log(this, "update");
        testTextView.setText(s);
    }

    @Override
    protected void onStop() {
        super.onStop();
        observer.dispose();
    }

    @Override
    public void update() {
        update("heh");
    }
}
