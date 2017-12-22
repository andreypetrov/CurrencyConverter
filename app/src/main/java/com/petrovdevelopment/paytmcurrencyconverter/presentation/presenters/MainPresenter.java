package com.petrovdevelopment.paytmcurrencyconverter.presentation.presenters;

import com.petrovdevelopment.paytmcurrencyconverter.domain.interactors.BaseObserver;
import com.petrovdevelopment.paytmcurrencyconverter.domain.usecases.DomainUseCaseFactory;

import com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.di.MainProvider;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.ui.MainView;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.usecases.PresentationUseCaseFactory;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.utils.PresenterUtils;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.viewmodels.CurrenciesWithTimestamp;
import com.petrovdevelopment.paytmcurrencyconverter.domain.models.Currency;

import com.petrovdevelopment.paytmcurrencyconverter.platform.utils.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Communication up to view happens only via interfaces. Presenter does not know anything of view implementation logic
 *
 * We have couple of options how to store Presenter - persist it even after activity is destroyed, in which case we can only store weak reference to view,
 * or make it live and die with Activity, in which case we can avoid the weak referencing.
 * Living and dying with view implies that persistence is going to happen only on a gateway/services layer based on in memory and file caching. Current implementation has only file caching.
 *
 * Created by Andrey on 2017-12-18.
 */

public class MainPresenter {
    private MainView mainView;
    private final DomainUseCaseFactory domainUseCaseFactory;

    private List<Currency> selectorCurrencies;
    private Map<String, Currency> currencyLookUp;
    private List<Currency> listCurrencies;

    private CurrenciesListObserver currenciesListObserver;

    public MainPresenter(MainProvider mainProvider, MainView mainView) {
        this.mainView = mainView;
        domainUseCaseFactory = mainProvider.getDomainUseCaseFactory();
        selectorCurrencies = new ArrayList<>();
        listCurrencies = new ArrayList<>();
    }

    //region ### view lifecycle callbacks

    public void onViewLoaded() {
        Log.log(this, "onViewLoaded");
        fetchSelectorCurrencies();
        updateCurrencySelectorView();
    }

    //get rid of the currency list observer when activity is in the background
    public void onViewUnloaded() {
        if (currenciesListObserver != null && !currenciesListObserver.isDisposed()) currenciesListObserver.dispose();
    }

    //endregion

    private void fetchListCurrencies(String currencyShortName) {
        showProgressIndicatorView();
        hideErrorView();
        Double currentAmount = PresenterUtils.amountToDouble(mainView.getAmount());
        Observable<CurrenciesWithTimestamp> listCurrenciesObservable = domainUseCaseFactory.exchangeRatesUseCase(currencyShortName).execute()
                .map(response -> PresentationUseCaseFactory.convertExchangeRateToCurrenciesUseCase(response, currencyLookUp, currentAmount).execute())
                .observeOn(AndroidSchedulers.mainThread());

        currenciesListObserver = new CurrenciesListObserver(this);
        listCurrenciesObservable.subscribe(currenciesListObserver);
    }

    private void fetchSelectorCurrencies() {
        selectorCurrencies = domainUseCaseFactory.localCurrenciesUseCase().execute();
        currencyLookUp = PresentationUseCaseFactory.createCurrenciesMapUseCase(selectorCurrencies).execute();
    }

    public void onAmountChanged(String amount) {
        PresentationUseCaseFactory.updateCurrencyAmountsUseCase(listCurrencies, PresenterUtils.amountToDouble(amount)).execute();
        updateCurrencyListView();
    }

    private static class CurrenciesListObserver extends BaseObserver<CurrenciesWithTimestamp> {
        final MainPresenter presenter;

        public CurrenciesListObserver(MainPresenter presenter) {
            this.presenter = presenter;
        }

        @Override
        public void onNext(CurrenciesWithTimestamp currenciesWithTimestamp) {
            Log.log(this, "onNext");
            presenter.listCurrencies = currenciesWithTimestamp.currencies;
            presenter.updateCurrencyListView();
            presenter.updateDate(currenciesWithTimestamp.date);
        }

        @Override
        public void onError(Throwable e) {
            //in an actual app this message should be converted in a more customer friendly message,
            // using a localized string and possibly assigning it an error code which can help phone support to investigate if customers are calling with complains
            presenter.showErrorView(e.getLocalizedMessage());
            presenter.listCurrencies.clear(); //We don't want to mislead the user with showing them stale quotes
            presenter.updateCurrencyListView();
            presenter.hideProgressIndicatorView();
        }

        @Override
        public void onComplete() {
            presenter.hideProgressIndicatorView();
        }
    }



    //region ### view updates. Redundant if we keep strong reference to view. We would have to add null checks to them otherwise

    private void updateCurrencyListView() {
        mainView.updateCurrencyList();
    }

    private void updateCurrencySelectorView() {
        mainView.updateCurrencySelector();
    }

    private void showProgressIndicatorView() {
        mainView.showProgressIndicator();
    }

    private void hideProgressIndicatorView() {
        mainView.hideProgressIndicator();
    }

    private void showErrorView(String errorMessage) {
        mainView.showError(errorMessage);
    }

    private void hideErrorView() {
        mainView.hideError();
    }

    private void updateDate(String date) {
        mainView.updateDate(date);
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
