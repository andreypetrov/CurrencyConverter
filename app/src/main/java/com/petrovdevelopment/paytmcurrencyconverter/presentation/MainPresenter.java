package com.petrovdevelopment.paytmcurrencyconverter.presentation;

import com.petrovdevelopment.paytmcurrencyconverter.domain.interactors.BaseObserver;
import com.petrovdevelopment.paytmcurrencyconverter.domain.usecases.ExchangeRatesUseCase;
import com.petrovdevelopment.paytmcurrencyconverter.platform.MainProvider;
import com.petrovdevelopment.paytmcurrencyconverter.platform.utils.Log;
import com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels.Currency;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.MainView;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.presenterusecases.ConverterExchangeRateToCurrenciesUseCase;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.presenterusecases.CreateCurrenciesMapUseCase;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.presenterusecases.LocalCurrenciesUseCase;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.presenterusecases.UpdateCurrencyAmountsUseCase;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Communication up to view happens only via interfaces. Presenter does not know anything of view implementation logic
 * Created by Andrey on 2017-12-18.
 */

public class MainPresenter {
    private WeakReference<MainView> mainView;
    private MainProvider mainProvider;

    private List<Currency> selectorCurrencies;
    private Map<String, Currency> currencyLookUp;
    private List<Currency> listCurrencies;

    private CurrenciesListObserver currenciesListObserver;

    //TODO persist state
    //State
    private double amount;
    private int currentSelectorCurrencyPosition = -1;//TODO optimize not to reload if same currency that has already been there is selected


    public void setView(MainView mainView) {
        this.mainView = new WeakReference<>(mainView);
    }

    public MainPresenter(MainProvider mainProvider) {
        this.mainProvider = mainProvider;
        selectorCurrencies = new ArrayList<>();
        listCurrencies = new ArrayList<>();
    }

    //region ### view lifecycle callbacks

    public void onViewLoaded() {
        Log.log(this, "onViewLoaded");
        fetchSelectorCurrenciesIfNeeded();
        updateCurrencySelectorView();
    }

    //get rid of the currency list observer when activity is in the background
    public void onViewUnloaded() {
        if (currenciesListObserver != null && !currenciesListObserver.isDisposed()) currenciesListObserver.dispose();
    }

    //endregion

    private void fetchListCurrenciesIfNeeded(String currencyShortName) {
        if (listCurrencies == null || listCurrencies.size() == 0) fetchListCurrencies(currencyShortName);
    }

    private void fetchSelectorCurrenciesIfNeeded() {
        if (selectorCurrencies == null || selectorCurrencies.size() == 0) fetchSelectorCurrencies();
    }

    private void fetchListCurrencies(String currencyShortName) {
        showProgressIndicator();
        hideError();
        Observable<List<Currency>> listCurrenciesObservable = new ExchangeRatesUseCase(mainProvider.getEntityGateway(), currencyShortName).execute()
                .map(response -> new ConverterExchangeRateToCurrenciesUseCase(response, currencyLookUp, amount).execute())
                .observeOn(AndroidSchedulers.mainThread());

        currenciesListObserver = new CurrenciesListObserver(this);
        listCurrenciesObservable.subscribe(currenciesListObserver);
    }

    private void fetchSelectorCurrencies() {
        selectorCurrencies = new LocalCurrenciesUseCase(mainProvider.getLocalGateway()).execute();
        currencyLookUp = new CreateCurrenciesMapUseCase(selectorCurrencies).execute();
    }

    public void onAmountChanged(String s) {
        try {
            amount = Double.valueOf(s);
        } catch (NumberFormatException e) { //if empty string or another invalid value
            amount = 0;
        }
        new UpdateCurrencyAmountsUseCase(listCurrencies, amount).execute();
        updateCurrencyListView();
    }

    private static class CurrenciesListObserver extends BaseObserver<List<Currency>> {
        MainPresenter presenter;

        public CurrenciesListObserver(MainPresenter presenter) {
            this.presenter = presenter;
        }

        @Override
        public void onNext(List<Currency> listCurrencies) {
            Log.log(this, "onNext");
            presenter.listCurrencies = listCurrencies;
            presenter.updateCurrencyListView();
        }

        @Override
        public void onError(Throwable e) {
            presenter.showError(e.getLocalizedMessage());
            presenter.listCurrencies.clear(); //We don't want to mislead the user with showing them stale quotes
            presenter.updateCurrencyListView();
            presenter.hideProgressIndicator();
        }

        @Override
        public void onComplete() {
            presenter.hideProgressIndicator();
        }
    }


    //region ### view updates

    private void updateCurrencyListView() {
        MainView view = mainView.get();
        if (view != null) view.updateCurrencyList();
    }

    private void updateCurrencySelectorView() {
        MainView view = mainView.get();
        if (view != null) view.updateCurrencySelector();
    }

    private void showProgressIndicator() {
        MainView view = mainView.get();
        if (view != null) view.showProgressIndicator();
    }

    private void hideProgressIndicator() {
        MainView view = mainView.get();
        if (view != null) view.hideProgressIndicator();
    }

    private void showError(String errorMessage) {
        MainView view = mainView.get();
        if (view != null) view.showError(errorMessage);
    }

    private void hideError() {
        MainView view = mainView.get();
        if (view != null) view.hideError();
    }

    //endregion


    //region ### adapter callbacks

    /**
     * From presenter's perspective there is no notion of spinner, as this is an android platform concept.
     * There is only the notion of an abstract selector - something that will allow you to select a currency index. View can decide whether this will be done via a spinner, action sheet, edit text, or something else.
     * Adapters are part of the view layer
     **/
    public void onSelectorCurrencySelected(int position) {
        String currencyShortName = selectorCurrencies.get(position).shortName;
        Log.log(this, "currency selected: " + currencyShortName);
        fetchListCurrencies(currencyShortName);
        updateCurrencyListView();
    }

    public int getSelectorCurrenciesCount() {
        return selectorCurrencies.size();
    }

    public long getSelectorCurrencyId(int i) {
        return i;
    }

    public Currency getSelectorCurrency(int i) {
        return selectorCurrencies.get(i);
    }

    /**
     * Again, presenter has no idea list of currencies is implemented as a grid view, they might have been in any other list form.
     */
    public Currency getListCurrency(int position) {
        return listCurrencies.get(position);
    }

    public int getListCurrenciesCount() {
        return listCurrencies.size();
    }
    //endregion

}
