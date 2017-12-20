package com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels;

import android.graphics.drawable.Drawable;

/**
 * Created by Andrey on 2017-12-19.
 */

public class CurrencyVM {
    public Drawable flag;
    public String shortName;
    public String longName;
    public String exchangeRate;
    public String amount;

//    public CurrencyVM(String shortName, String longName, Drawable flagResourceId) {
//        this.shortName = shortName;
//        this.longName = longName;
//        this.flagResourceId = flagResourceId;
//        exchangeRate = "1.35";
//        amount = "232323232323233423213asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfadfadfa234523231421341234213412341234123412342134232.23";
//    }

    public CurrencyVM(String shortName, String longName, Drawable flag) {
        this.flag = flag;
        this.shortName = shortName;
        this.longName = longName;
        exchangeRate = "1.35";
        amount = "232323232323233423213asdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfasdfadfadfa234523231421341234213412341234123412342134232.23";
    }
}
