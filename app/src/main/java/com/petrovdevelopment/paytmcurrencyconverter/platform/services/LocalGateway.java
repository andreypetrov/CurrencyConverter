package com.petrovdevelopment.paytmcurrencyconverter.platform.services;

import android.content.Context;
import android.content.res.Resources;

import com.petrovdevelopment.paytmcurrencyconverter.R;
import com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels.CurrencyVM;

import java.util.ArrayList;
import java.util.List;

/**
 * Retrieve list of allowed currencies from local resources (xml files).
 * In a full app we might have used a prebuilt local database for that.
 * Created by Andrey on 2017-12-19.
 */

public class LocalGateway {

    public static List<CurrencyVM> getCurrencies(Context context) {
        Resources r = context.getApplicationContext().getResources();
        int[] currencyFlags = r.getIntArray(R.array.currency_flags);
        String[] currencyShortNames = r.getStringArray(R.array.currencies_short);
        String[] currencyLongNames = r.getStringArray(R.array.currencies_long);
        if (!isValid(currencyFlags, currencyShortNames, currencyLongNames))
            throw new IllegalStateException("You should provide a matching length of currency flags, long names and short names. Check your arrays.xml!");
        return createCurrencyViewModels(currencyFlags, currencyShortNames, currencyLongNames);
    }

    private static List<CurrencyVM> createCurrencyViewModels(int[] currencyFlags, String[] currencyShortNames, String[] currencyLongNames) {
        List<CurrencyVM> currencyVMList = new ArrayList<>();
        for (int i = 0; i < currencyFlags.length; i++) {
            currencyVMList.add(new CurrencyVM(currencyShortNames[i], currencyLongNames[i], currencyFlags[i]));
        }
        return currencyVMList;
    }

    /**
     * Validate we have at least one currency and that data in array.xml is not malformed (i.e. non-matching number of currency flags, short names and long names.
     *
     * @param currencyFlags
     * @param currencyShortNames
     * @param currencyLongNames
     * @return
     */
    private static boolean isValid(int[] currencyFlags, String[] currencyShortNames, String[] currencyLongNames) {
        if (currencyFlags == null || currencyShortNames == null || currencyLongNames == null || currencyFlags.length == 0)
            return false;
        return currencyFlags.length == currencyShortNames.length && currencyFlags.length == currencyLongNames.length;
    }

}
