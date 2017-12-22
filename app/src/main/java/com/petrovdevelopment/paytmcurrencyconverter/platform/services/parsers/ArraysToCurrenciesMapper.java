package com.petrovdevelopment.paytmcurrencyconverter.platform.services.parsers;

import android.content.res.TypedArray;

import com.petrovdevelopment.paytmcurrencyconverter.domain.models.Currency;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 2017-12-22.
 * Coding is fun!
 */

public class ArraysToCurrenciesMapper {
    private static final String XML_ERROR_MESSAGE = "You should provide a matching length of currency flags, long names and short names. Check your arrays.xml!";

    public static List<Currency> convertToCurrencies(TypedArray currencyFlags, String[] currencyShortNames, String[] currencyLongNames) {
        if (!isValid(currencyFlags, currencyShortNames, currencyLongNames)) throw new XmlLocalException();

        List<Currency> currencyList = new ArrayList<>();
        for (int i = 0; i < currencyFlags.length(); i++) {
            currencyList.add(new Currency(currencyShortNames[i], currencyLongNames[i], currencyFlags.getDrawable(i)));
        }
        return currencyList;
    }

    /**
     * Validate we have at least one currency and that data in array.xml is not malformed (i.e. non-matching number of currency flags, short names and long names.
     *
     * @param currencyFlags array of currency flags
     * @param currencyShortNames array of short names
     * @param currencyLongNames array of long names
     * @return true if data in xml is well formed, false if it is malformed
     */
    private static boolean isValid(TypedArray currencyFlags, String[] currencyShortNames, String[] currencyLongNames) {
        return currencyFlags != null && currencyShortNames != null && currencyLongNames != null && currencyFlags.length() > 0
                && currencyFlags.length() == currencyShortNames.length && currencyFlags.length() == currencyLongNames.length;
    }

    public static class XmlLocalException extends Error {
        public XmlLocalException() {
            super(ArraysToCurrenciesMapper.XML_ERROR_MESSAGE);
        }
    }
}
