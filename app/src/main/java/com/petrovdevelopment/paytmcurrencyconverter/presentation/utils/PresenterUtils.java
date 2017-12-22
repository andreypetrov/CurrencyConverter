package com.petrovdevelopment.paytmcurrencyconverter.presentation.utils;

import java.util.Locale;

/**
 * Created by Andrey on 2017-12-21.
 * Coding is fun!
 */

public class PresenterUtils {

    public static String toString(Double d) {
        return String.format(Locale.getDefault(),"%.2f",d);
    }

    public static Double amountToDouble(String amount) {
        try {
            return Double.valueOf(amount);
        } catch (NumberFormatException e) { //if empty string or another invalid value
            return 0.0d;
        }
    }
}
