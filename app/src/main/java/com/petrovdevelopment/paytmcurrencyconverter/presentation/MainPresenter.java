package com.petrovdevelopment.paytmcurrencyconverter.presentation;
import com.petrovdevelopment.paytmcurrencyconverter.platform.MainProvider;
import com.petrovdevelopment.paytmcurrencyconverter.platform.utilities.L;
import com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels.CurrencyVM;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.MainView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Communication up to view happens only via interfaces. Presenter does not know anything of view implementation logic
 * Created by Andrey on 2017-12-18.
 */

public class MainPresenter {
    private List<CurrencyVM> selectorCurrencies;
    private List<CurrencyVM> listCurrencies;

    private WeakReference<MainView> mainView;
    private WeakReference<MainProvider> mainProvider;

    public void setView(MainView mainView) {
        this.mainView = new WeakReference<>(mainView);
    }
    public void setProvider(MainProvider mainProvider) {
        this.mainProvider = new WeakReference<>(mainProvider);
    }

    public MainPresenter() {
        selectorCurrencies = new ArrayList<>();
        listCurrencies = new ArrayList<>();
    }

    public void viewReady() {
        L.log(this, "viewReady");
        //TODO switch to usecases later and introduce asynchronous updates

        loadLocalModels();
        updateCurrencySelectorView();
        updateCurrencyListView();
    }

    private void updateCurrencyListView() {
        MainView view = mainView.get();
        if (view != null) view.updateCurrencyList();
    }

    private void updateCurrencySelectorView() {
        MainView view = mainView.get();
        if (view != null) view.updateCurrencySelector();
    }

    private void loadLocalModels() {
        MainProvider provider = mainProvider.get();
        if (provider != null) {
            selectorCurrencies = mainProvider.get().getLocalGateway().getCurrencies();
            listCurrencies = mainProvider.get().getLocalGateway().getCurrencies();//TODO this in the future will happen over network upon selection in the selector
        }
    }





    /**
     * From presenter's perspective there is no notion of spinner, as this is an android platform concept.
     * There is only the notion of an abstract selector - something that will allow you to select a currency index. View can decide whether this will be done via a spinner, action sheet, edit text, or something else.
     * Adapters are part of the view layer**/
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
     *  Again, presenter has no idea list of currencies is implemented as a grid view, they might have been in any other list form.
     */
    public CurrencyVM getListCurrency(int position) {
        return listCurrencies.get(position);
    }
    public int getListCurrenciesCount() {
        return listCurrencies.size();
    }

}
