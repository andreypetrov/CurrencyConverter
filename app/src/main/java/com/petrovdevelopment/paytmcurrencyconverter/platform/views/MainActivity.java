package com.petrovdevelopment.paytmcurrencyconverter.platform.views;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.petrovdevelopment.paytmcurrencyconverter.R;
import com.petrovdevelopment.paytmcurrencyconverter.platform.adapters.CurrenciesCardAdapter;
import com.petrovdevelopment.paytmcurrencyconverter.platform.adapters.CurrenciesSpinnerAdapter;
import com.petrovdevelopment.paytmcurrencyconverter.platform.utilities.L;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.MainPresenter;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.MainView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends BaseActivity implements MainView {
    MainPresenter presenter;
    DisposableObserver<String> observer;
    ProgressBar progressBar;
    RecyclerView currenciesRecyclerView;
    Spinner currenciesSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        currenciesRecyclerView  = findViewById(R.id.currenciesRecyclerView);
        currenciesSpinner = findViewById(R.id.currenciesSpinner);

        assembleModule();
        configureCurrenciesSpinner();
        configureCurrenciesRecylcerView();
    }

    private void assembleModule() {
        presenter = new MainPresenter(); //TODO replace this with injection
        presenter.setView(this);
        presenter.setProvider(getApp());
    }


    private void configureCurrenciesSpinner() {
        BaseAdapter adapter = new CurrenciesSpinnerAdapter(presenter);
        currenciesSpinner.setAdapter(adapter);
        currenciesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    presenter.onSelectorCurrencySelected(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });
    }

    private void configureCurrenciesRecylcerView() {
        GridLayoutManager layoutManger = new GridLayoutManager(this, 2);
        currenciesRecyclerView.setLayoutManager(layoutManger);
        RecyclerView.Adapter adapter = new CurrenciesCardAdapter(presenter);
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



    public void update(String s) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        observer.dispose();
    }

    @Override
    public void updateCurrencyList() {
        currenciesRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void updateCurrencySelector() {
        L.log(this, "updateCurrencySelector");
        ((BaseAdapter) currenciesSpinner.getAdapter()).notifyDataSetChanged();
    }
}
