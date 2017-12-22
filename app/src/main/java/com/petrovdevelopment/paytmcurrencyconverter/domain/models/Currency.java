package com.petrovdevelopment.paytmcurrencyconverter.domain.models;

import android.graphics.drawable.Drawable;

/**
 * Created by Andrey on 2017-12-19.
 * Coding is fun!
 */

public class Currency {
    public Drawable flag; //TODO replace drawable by resource id to make model view-independent
    public final String shortName;
    public String longName;
    public String exchangeRate;
    public String amount;

    public Currency(String shortName) {
        this.shortName = shortName;
    }

    public Currency(String shortName, String longName, Drawable flag) {
        this.flag = flag;
        this.shortName = shortName;
        this.longName = longName;
        exchangeRate = "0.0";
        amount = "0.0";
    }
}
