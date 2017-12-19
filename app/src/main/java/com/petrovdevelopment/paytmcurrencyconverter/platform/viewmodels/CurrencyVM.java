package com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels;

/**
 * Created by Andrey on 2017-12-19.
 */

public class CurrencyVM {
    String shortName;
    String longName;
    int flagResourceId;
    double exchangeRate;
    double amount;

    public CurrencyVM(String shortName, String longName, int flagResourceId) {
        this.shortName = shortName;
        this.longName = longName;
        this.flagResourceId = flagResourceId;
    }

    public CurrencyVM(String shortName, String longName, int flagResourceId, double exchangeRate, double amount) {
        this.shortName = shortName;
        this.longName = longName;
        this.flagResourceId = flagResourceId;
        this.exchangeRate = exchangeRate;
        this.amount = amount;
    }
}
