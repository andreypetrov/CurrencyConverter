package com.petrovdevelopment.paytmcurrencyconverter.platform.services.models;

import java.util.List;

/**
 * Created by Andrey on 2017-12-19.
 */

public class ExchangeRatesResponse {
    private String base;
    private String date;
    private List<Rate> rates;

    public ExchangeRatesResponse() {
    }

    public ExchangeRatesResponse(String base, String date, List<Rate> rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }
}
