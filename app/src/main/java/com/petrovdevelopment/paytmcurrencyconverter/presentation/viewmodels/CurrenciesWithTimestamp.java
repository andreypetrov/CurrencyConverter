package com.petrovdevelopment.paytmcurrencyconverter.presentation.viewmodels;

import java.util.List;

/**
 * Created by Andrey on 2017-12-21.
 * Coding is fun!
 */

public class CurrenciesWithTimestamp {
    public String date;
    public List<Currency> currencies;

    public CurrenciesWithTimestamp(String date, List<Currency> currencies) {
        this.date = date;
        this.currencies = currencies;
    }
}
