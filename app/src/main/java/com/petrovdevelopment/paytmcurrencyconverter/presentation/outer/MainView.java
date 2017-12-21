package com.petrovdevelopment.paytmcurrencyconverter.presentation.outer;

import com.petrovdevelopment.paytmcurrencyconverter.platform.MainApplication;
import com.petrovdevelopment.paytmcurrencyconverter.platform.MainProvider;

/**
 * Created by Andrey on 2017-12-18.
 */

public interface MainView extends BaseView {
    void updateCurrencyList();
    void updateCurrencySelector();
    void showProgressIndicator();
    void hideProgressIndicator();
}
