package com.petrovdevelopment.paytmcurrencyconverter.presentation.utils;

import com.petrovdevelopment.paytmcurrencyconverter.domain.utils.CurrencyUtils;

import java.util.Locale;

/**
 * Created by Andrey on 2017-12-21.
 */

public class PresenterUtils {

    public static String toString(Double d) {
        return String.format(Locale.getDefault(),"%.2f",d);
    }
}
