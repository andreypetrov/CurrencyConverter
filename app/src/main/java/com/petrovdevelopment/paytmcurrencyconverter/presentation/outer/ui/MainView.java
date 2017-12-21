package com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.ui;

/**
 * Created by Andrey on 2017-12-18.
 */

public interface MainView extends BaseView {
    void updateCurrencyList();
    void updateCurrencySelector();
    void showProgressIndicator();
    void hideProgressIndicator();
    String getAmount();
    int getCurrentSelectorCurrencyPosition();
}
