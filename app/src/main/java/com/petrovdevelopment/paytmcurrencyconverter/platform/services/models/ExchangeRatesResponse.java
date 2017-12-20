package com.petrovdevelopment.paytmcurrencyconverter.platform.services.models;

import java.util.List;
import java.util.Map;

/**
 * Created by Andrey on 2017-12-19.
 */

public class ExchangeRatesResponse {
    private String base;
    private String date;
    private Map<String, Double> rates;

    public ExchangeRatesResponse() {
    }

    public ExchangeRatesResponse(String base, String date, Map<String, Double> rates) {
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

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "ExchangeRatesResponse{" +
                "base='" + base + '\'' +
                ", date='" + date + '\'' +
                ", rates=" + rates +
                '}';
    }
}
