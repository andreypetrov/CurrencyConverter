package com.petrovdevelopment.paytmcurrencyconverter.presentation;

import com.petrovdevelopment.paytmcurrencyconverter.platform.MainProvider;
import com.petrovdevelopment.paytmcurrencyconverter.platform.adapters.CurrenciesSpinnerAdapter;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.LocalGateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.utilities.L;
import com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels.CurrencyVM;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.CurrencySelectorItemView;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.MainView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 2017-12-18.
 */

public class MainPresenter {
    private List<CurrencyVM> currencyVMSpinnerList;

    private WeakReference<MainView> mainView;
    private WeakReference<MainProvider> mainProvider;

    public void setView(MainView mainView) {
        this.mainView = new WeakReference<>(mainView);
    }
    public void setProvider(MainProvider mainProvider) {
        this.mainProvider = new WeakReference<>(mainProvider);
    }

    public MainPresenter() {
        currencyVMSpinnerList = new ArrayList<>();
    }

    public void viewReady() {

        L.log(this, "viewReady");
        //TODO switch to usecases later and introduce asynchronous updates

        loadLocalModels();
        updateCurrencySelector();
    }

    private void updateCurrencySelector() {
        if (mainView.get() != null) mainView.get().updateCurrencySelector();
    }


    private void loadLocalModels() {
        currencyVMSpinnerList = mainProvider.get().getLocalGateway().getCurrencies();
    }








    /** Callbacks for the view to get the currency selector updated. This allows the view to delegate all "thinking" to the presenter and stay as stupid as possible.
     * From presenter's perspective there is no notion of spinner, as this is an android platform concept.
     * There is only the notion of an abstract selector - something that will allow you to select one of many currencies. View can decide whether this will be done via a selector or something else.
     * Adapters are part of the view layer**/
    public int getSelectorCurrenciesCount() {
        return currencyVMSpinnerList.size();
    }
    public Object getSelectorCurrency(int i) {
        return currencyVMSpinnerList.get(i);
    }
    public long getSelectorCurrencyId(int i) {
        return i;
    }
    public void configureSelectorCell(CurrencySelectorItemView currencySelectorItemView, int position) {
        CurrencyVM currencyVM = currencyVMSpinnerList.get(position);
        currencySelectorItemView.displayFlag(currencyVM.flagResourceId);
        currencySelectorItemView.displayShortName(currencyVM.shortName);
    }
}
