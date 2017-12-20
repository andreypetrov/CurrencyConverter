package com.petrovdevelopment.paytmcurrencyconverter.presentation;

import com.petrovdevelopment.paytmcurrencyconverter.domain.interactors.BaseObserver;
import com.petrovdevelopment.paytmcurrencyconverter.domain.usecases.ExchangeRatesUseCase;
import com.petrovdevelopment.paytmcurrencyconverter.platform.MainProvider;
import com.petrovdevelopment.paytmcurrencyconverter.platform.utilities.L;
import com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels.CurrencyVM;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.MainView;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.usecases.LocalCurrenciesUseCase;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Communication up to view happens only via interfaces. Presenter does not know anything of view implementation logic
 * Created by Andrey on 2017-12-18.
 */

public class MainPresenter {
    private WeakReference<MainView> mainView;
    private MainProvider mainProvider;



    private List<CurrencyVM> selectorCurrencies;
    private List<CurrencyVM> listCurrencies;

    private CurrenciesListObserver currenciesListObserver;

    public void setView(MainView mainView) {
        this.mainView = new WeakReference<>(mainView);
    }

    public MainPresenter(MainProvider mainProvider) {
        this.mainProvider = mainProvider;
        selectorCurrencies = new ArrayList<>();
        listCurrencies = new ArrayList<>();
    }

    /**
     * Lifecycle methods, called from view
     */
    public void onViewStarted() {
        L.log(this, "onViewStarted");
        fetchSelectorCurrenciesIfNeeded();
        updateCurrencySelectorView();
        fetchListCurrenciesIfNeeded();
        updateCurrencyListView();
    }

    private void fetchListCurrenciesIfNeeded() {
        if (listCurrencies == null || listCurrencies.size() == 0) fetchListCurrencies();
    }

    private void fetchSelectorCurrenciesIfNeeded() {
        if (selectorCurrencies == null || selectorCurrencies.size() == 0) fetchSelectorCurrencies();
    }

    private void fetchListCurrencies() {
        new LocalCurrenciesUseCase(mainProvider.getLocalGateway()).execute();
        showProgressIndicator();
//            listCurrencies = mainProvider.get().getLocalGateway().getCurrencies(); //TODO use proper network call here

    }

    private void fetchSelectorCurrencies() {
        selectorCurrencies = new LocalCurrenciesUseCase(mainProvider.getLocalGateway()).execute();
    }

    public void onViewStopped() {
        if(currenciesListObserver != null && !currenciesListObserver.isDisposed())  currenciesListObserver.dispose();
    }

    public static class CurrenciesListObserver extends BaseObserver<List<CurrencyVM>> {
        MainPresenter presenter;

        public CurrenciesListObserver(MainPresenter presenter) {
            this.presenter = presenter;
        }

        @Override
        public void onNext(List<CurrencyVM> listCurrencies) {
            presenter.listCurrencies = listCurrencies;
            presenter.updateCurrencyListView();
        }

        @Override
        public void onError(Throwable e) {
            presenter.hideProgressIndicator();
        }

        @Override
        public void onComplete() {
            presenter.hideProgressIndicator();
        }
    }


    //region view updates


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
    //endregion




    //region adapter callbacks

    /**
     * From presenter's perspective there is no notion of spinner, as this is an android platform concept.
     * There is only the notion of an abstract selector - something that will allow you to select a currency index. View can decide whether this will be done via a spinner, action sheet, edit text, or something else.
     * Adapters are part of the view layer
     **/
    public void onSelectorCurrencySelected(int position) {
        L.log(this, "currency selected: " + selectorCurrencies.get(position).shortName);
    }

    public int getSelectorCurrenciesCount() {
        return selectorCurrencies.size();
    }

    public long getSelectorCurrencyId(int i) {
        return i;
    }

    public CurrencyVM getSelectorCurrency(int i) {
        return selectorCurrencies.get(i);
    }

    /**
     * Again, presenter has no idea list of currencies is implemented as a grid view, they might have been in any other list form.
     */
    public CurrencyVM getListCurrency(int position) {
        return listCurrencies.get(position);
    }

    public int getListCurrenciesCount() {
        return listCurrencies.size();
    }
    //endregion

}
