package com.petrovdevelopment.paytmcurrencyconverter.presentation.outer;

/**
 * Created by Andrey on 2017-12-20.
 */

public interface CurrencyListItemView extends CurrencySelectorItemView {
    void displayExchangeRate(String exchangeRate);
    void displayAmount(String amount);
}
