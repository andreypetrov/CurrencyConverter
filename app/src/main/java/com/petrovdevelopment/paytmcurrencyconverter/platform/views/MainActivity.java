package com.petrovdevelopment.paytmcurrencyconverter.platform.views;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.petrovdevelopment.paytmcurrencyconverter.R;
import com.petrovdevelopment.paytmcurrencyconverter.platform.adapters.CurrenciesCardAdapter;
import com.petrovdevelopment.paytmcurrencyconverter.platform.adapters.CurrenciesSpinnerAdapter;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.MainPresenter;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.MainView;
import io.reactivex.observers.DisposableObserver;

public class MainActivity extends BaseActivity implements MainView {
    private MainPresenter presenter;

    private ProgressBar progressBar;
    private TextView errorView;

    private RecyclerView currenciesRecyclerView;
    private Spinner currenciesSpinner;
    private ShimmerFrameLayout shimmerContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        currenciesRecyclerView  = findViewById(R.id.currenciesRecyclerView);
        currenciesSpinner = findViewById(R.id.currenciesSpinner);
        shimmerContainer = findViewById(R.id.shimmerContainer);
        errorView = findViewById(R.id.errorView);
        assembleModule();
        configureCurrenciesSpinner();
        configureCurrenciesRecylcerView();
    }

    private void assembleModule() {
        presenter = new MainPresenter(getApp()); //TODO replace this with dagger injection if time permits.
        presenter.setView(this);
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
        presenter.onViewStarted();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onViewStopped();
    }


    /**
     * Interface methods, exposed to presenter.
     */

    @Override
    public void updateCurrencyList() {
        currenciesRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void updateCurrencySelector() {
        ((BaseAdapter) currenciesSpinner.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void showProgressIndicator() {
        progressBar.setVisibility(View.VISIBLE);
        shimmerContainer.startShimmerAnimation();
    }

    @Override
    public void hideProgressIndicator() {
        progressBar.setVisibility(View.INVISIBLE);//INVISIBLE instead of GONE so that the recycler view does not shift up
        shimmerContainer.stopShimmerAnimation();
    }

    @Override
    public void showError(String errorMessage) {
        errorView.setText(errorMessage);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        errorView.setVisibility(View.GONE);
        errorView.setText("");
    }


}
