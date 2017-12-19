package com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels;

import android.graphics.drawable.Drawable;

/**
 * Created by Andrey on 2017-12-19.
 */

public class CurrencyVM {
    public String shortName;
    public String longName;
    public Drawable flagResourceId;
    public String exchangeRate;
    public String amount;

    public CurrencyVM(String shortName, String longName, Drawable flagResourceId) {
        this.shortName = shortName;
        this.longName = longName;
        this.flagResourceId = flagResourceId;
    }

}
