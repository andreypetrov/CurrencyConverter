package com.petrovdevelopment.paytmcurrencyconverter.platform.services.models;

/**
 * Created by Andrey on 2017-12-20.
 */

public class Rate {
    private String shortName;
    private double exchangeRate;

    public Rate(String shortName, double exchangeRate) {
        this.shortName = shortName;
        this.exchangeRate = exchangeRate;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
