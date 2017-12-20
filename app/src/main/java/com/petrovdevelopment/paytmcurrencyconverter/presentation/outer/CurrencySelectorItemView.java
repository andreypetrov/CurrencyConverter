package com.petrovdevelopment.paytmcurrencyconverter.presentation.outer;

import android.graphics.drawable.Drawable;

/**
 * Created by Andrey on 2017-12-20.
 */

public interface CurrencySelectorItemView {
    void displayFlag(Drawable flag); //TODO fix drawable reference if time allows?
    void displayShortName(String shortName);
}
