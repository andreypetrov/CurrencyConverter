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
        exchangeRate = "1.35";
        amount = "232323232323233423213asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfadfadfa234523231421341234213412341234123412342134232.23";
    }

}
