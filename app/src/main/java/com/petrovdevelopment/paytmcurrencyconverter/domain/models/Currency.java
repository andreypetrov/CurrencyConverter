package com.petrovdevelopment.paytmcurrencyconverter.domain.models;

import android.graphics.drawable.Drawable;

/**
 * In the current implementation it is not an issue we keep the drawables retrieved from local resources. Potentially (if we had e.g. 1000 currencies) this can be rewritten to keep only flag resource id and thus reduce memory footprint.
 *
 * Created by Andrey on 2017-12-19.
 * Coding is fun!
 */

public class Currency {
    public Drawable flag;
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
