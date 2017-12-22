package com.petrovdevelopment.paytmcurrencyconverter.platform.ui.views;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.petrovdevelopment.paytmcurrencyconverter.R;
import com.petrovdevelopment.paytmcurrencyconverter.platform.ui.adapters.CurrenciesCardAdapter;
import com.petrovdevelopment.paytmcurrencyconverter.platform.ui.adapters.CurrenciesSpinnerAdapter;
import com.petrovdevelopment.paytmcurrencyconverter.platform.ui.viewmodel.MainActivityState;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.presenters.MainPresenter;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.ui.MainView;

public class MainActivity extends BaseActivity implements MainView {
    private MainPresenter presenter;

    private ProgressBar progressBar;
    private TextView errorView;

    private EditText amountView;
    private RecyclerView currenciesRecyclerView;
    private Spinner currenciesSpinner;
    private ShimmerFrameLayout shimmerContainer;
    private TextView dateView;

    //TODO simplify card view to improve scrolling?

    private MainActivityState viewState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountView = findViewById(R.id.amountView);
        currenciesSpinner = findViewById(R.id.currenciesSpinner);
        progressBar = findViewById(R.id.progressBar);
        errorView = findViewById(R.id.errorView);
        currenciesRecyclerView = findViewById(R.id.currenciesRecyclerView);
        dateView = findViewById(R.id.dateView);
        shimmerContainer = findViewById(R.id.shimmerContainer);

        assembleModule();
        configureAmountView();
        configureCurrenciesSpinner();
        configureCurrenciesRecyclerView();

        initViewState(savedInstanceState);
    }

    /**
     * Recover previous state if such exists, else create new state
     * @param savedInstanceState previous state or null
     */
    private void initViewState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            viewState = MainActivityState.createFromBundle(savedInstanceState);
        } else {
            viewState = new MainActivityState("", 0);
        }
    }

    private void assembleModule() {
        presenter = new MainPresenter(getApp(), this); //TODO replace this with dagger injection if time permits.
    }

    private void configureAmountView() {
        amountView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewState.setAmount(String.valueOf(charSequence));
                presenter.onAmountChanged(viewState.getAmount());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void configureCurrenciesSpinner() {
        BaseAdapter adapter = new CurrenciesSpinnerAdapter(presenter);
        currenciesSpinner.setAdapter(adapter);
        currenciesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewState.setCurrentSelectorPosition(position);
                presenter.onSelectorCurrencySelected(viewState.getCurrentSelectorPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });
    }

    //TODO add fancy animation on loading if time permits
    private void configureCurrenciesRecyclerView() {
        GridLayoutManager layoutManger = new GridLayoutManager(this, 2);
        currenciesRecyclerView.setLayoutManager(layoutManger);
        RecyclerView.Adapter adapter = new CurrenciesCardAdapter(presenter);
        currenciesRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onViewLoaded();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onViewUnloaded();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        viewState.storeToBundle(outState);
        super.onSaveInstanceState(outState);
    }


    //region ### MainView interface

    @Override
    public String getAmount() {
        return viewState.getAmount();
    }

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
    public void updateDate(String date) {
        dateView.setText(getResources().getString(R.string.as_of, date));
    }

    @Override
    public void showError(String errorMessage) {
        errorView.setText(errorMessage);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        errorView.setVisibility(View.GONE);
    }
    //endregion


}
