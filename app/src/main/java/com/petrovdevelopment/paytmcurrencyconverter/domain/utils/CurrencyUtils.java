package com.petrovdevelopment.paytmcurrencyconverter.domain.utils;

/**
 * Created by Andrey on 2017-12-20.
 * Coding is fun!
 */

public class CurrencyUtils {
    public static double convert(double amount, double exchangeRate) {
        return amount*exchangeRate;
    }
}
