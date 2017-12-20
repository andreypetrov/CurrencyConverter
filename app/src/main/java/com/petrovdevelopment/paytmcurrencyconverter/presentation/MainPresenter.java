package com.petrovdevelopment.paytmcurrencyconverter.presentation;
import com.petrovdevelopment.paytmcurrencyconverter.platform.MainProvider;
import com.petrovdevelopment.paytmcurrencyconverter.platform.utilities.L;
import com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels.CurrencyVM;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.CurrencyListItemView;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.CurrencySelectorItemView;
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
        updateCurrencySelector();
    }

    private void updateCurrencySelector() {
        if (mainView.get() != null) mainView.get().updateCurrencySelector();
    }

    private void loadLocalModels() {
        selectorCurrencies = mainProvider.get().getLocalGateway().getCurrencies();
        listCurrencies = mainProvider.get().getLocalGateway().getCurrencies();//TODO this in the future will happen over network upon selection in the selector
    }

    /** Callbacks for the view to get the currency selector updated. This allows the view to delegate all "thinking" to the presenter and stay as stupid as possible.
     * From presenter's perspective there is no notion of spinner, as this is an android platform concept.
     * There is only the notion of an abstract selector - something that will allow you to select one of many currencies. View can decide whether this will be done via a selector or something else.
     * Adapters are part of the view layer**/
    public int getSelectorCurrenciesCount() {
        return selectorCurrencies.size();
    }
    public Object getSelectorCurrency(int i) {
        return selectorCurrencies.get(i);
    }
    public long getSelectorCurrencyId(int i) {
        return i;
    }
    public void configureSelectorCurrencyItem(CurrencySelectorItemView itemView, int position) {
        CurrencyVM currencyVM = selectorCurrencies.get(position);
        itemView.displayFlag(currencyVM.flagResourceId);
        itemView.displayShortName(currencyVM.shortName);
    }


    /**
     * Same methods we have for the spinner are required for the list of currencies on screen. Again, presenter has no idea they are implemented as a grid view, they might have been in any other list form.
     *
     */
    public int getListCurrenciesCount() {
        return listCurrencies.size();
    }
    public void configureListCurrencyCell(CurrencyListItemView itemView, int position) {
        CurrencyVM currencyVM = selectorCurrencies.get(position);
        itemView.displayFlag(currencyVM.flagResourceId);
        itemView.displayShortName(currencyVM.shortName);
        itemView.displayExchangeRate(currencyVM.exchangeRate);
        itemView.displayAmount(currencyVM.amount);
    }

    public void onCurrencySelectorSelected(int position) {
        L.log(this, "currency selected: " + selectorCurrencies.get(position).shortName);
    }
}
