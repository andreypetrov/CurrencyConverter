package com.petrovdevelopment.paytmcurrencyconverter.presentation.observers;

import com.petrovdevelopment.paytmcurrencyconverter.platform.utils.Log;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.presenters.MainPresenter;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.viewmodels.CurrenciesWithTimestamp;

/**
 * Created by Andrey on 2017-12-21.
 * Coding is fun!
 */
public class CurrenciesListObserver extends BaseObserver<CurrenciesWithTimestamp> {
    final MainPresenter presenter;

    public CurrenciesListObserver(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onNext(CurrenciesWithTimestamp currenciesWithTimestamp) {
        Log.log(this, "onNext");
        presenter.setListCurrencies(currenciesWithTimestamp.currencies);
        presenter.updateCurrencyListView();
        presenter.updateDate(currenciesWithTimestamp.date);
    }

    @Override
    public void onError(Throwable e) {
        //in an actual app this message should be converted in a more customer friendly message,
        // using a localized string and possibly assigning it an error code which can help phone support to investigate if customers are calling with complains
        presenter.showErrorView(e.getLocalizedMessage());
        presenter.getListCurrencies().clear(); //We don't want to mislead the user with showing them stale quotes
        presenter.updateCurrencyListView();
        presenter.hideProgressIndicatorView();
    }

    @Override
    public void onComplete() {
        presenter.hideProgressIndicatorView();
    }
}

