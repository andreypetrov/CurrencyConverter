package com.petrovdevelopment.paytmcurrencyconverter.platform.services.gateways;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.petrovdevelopment.paytmcurrencyconverter.R;
import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.SynchronousGateway;
import com.petrovdevelopment.paytmcurrencyconverter.domain.models.Currency;

import java.util.ArrayList;
import java.util.List;

/**
 * Retrieve list of allowed currencies from local resources (xml files).
 * In a full app we might have used a prebuilt local database for that.
 * Created by Andrey on 2017-12-19.
 */

public class XmlLocalGateway implements SynchronousGateway {
    private final Context context;
    private static final String XML_ERROR_MESSAGE = "You should provide a matching length of currency flags, long names and short names. Check your arrays.xml!";
    public XmlLocalGateway(Context context) {
        this.context = context;
    }

    /**
     * Current use case is simple enough to allow us to keep the flag drawables in our view model.
     * If we had to optimize then we could pass only resource ids and push the responsibility of retrieving the actual drawables to the adapters. For now let's not optimize prematurely :)
     *
     * @return list of currencies
     */
    public List<Currency> getCurrencies() {
        Resources r = context.getApplicationContext().getResources();
        TypedArray currencyFlags = r.obtainTypedArray(R.array.currency_flags);
        String[] currencyShortNames = r.getStringArray(R.array.currencies_short);
        String[] currencyLongNames = r.getStringArray(R.array.currencies_long);
        if (!isValid(currencyFlags, currencyShortNames, currencyLongNames)) {
            throw new IllegalStateException(XML_ERROR_MESSAGE);
        }
        return createCurrencyViewModels(currencyFlags, currencyShortNames, currencyLongNames);
    }

    private static List<Currency> createCurrencyViewModels(TypedArray currencyFlags, String[] currencyShortNames, String[] currencyLongNames) {
        List<Currency> currencyList = new ArrayList<>();
        for (int i = 0; i < currencyFlags.length(); i++) {
            currencyList.add(new Currency(currencyShortNames[i], currencyLongNames[i], currencyFlags.getDrawable(i)));
        }
        return currencyList;
    }

    /**
     * Validate we have at least one currency and that data in array.xml is not malformed (i.e. non-matching number of currency flags, short names and long names.
     * TODO move validation logic out in its own validator class
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
}
