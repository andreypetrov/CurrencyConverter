package com.petrovdevelopment.paytmcurrencyconverter.platform.services.gateways;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.petrovdevelopment.paytmcurrencyconverter.R;
import com.petrovdevelopment.paytmcurrencyconverter.domain.outer.gateways.SynchronousGateway;
import com.petrovdevelopment.paytmcurrencyconverter.domain.models.Currency;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.parsers.ArraysToCurrenciesMapper;

import java.util.List;

/**
 * Retrieve list of allowed currencies from local resources (xml files).
 * In a full app we might have used a prebuilt local database for that.
 * Created by Andrey on 2017-12-19.
 */

public class XmlLocalGateway implements SynchronousGateway {
    private final Context context;
    public XmlLocalGateway(Context context) {
        this.context = context;
    }

    /**
     * Current use case is simple enough to allow us to keep the flag drawables in our view model.
     * If we had to optimize then we could pass only resource ids and push the responsibility of retrieving the actual drawables to the adapters. For now let's not optimize prematurely :)
     *
     * @return list of currencies
     */
    public List<Currency> getCurrencies() throws ArraysToCurrenciesMapper.XmlLocalException {
        Resources r = context.getApplicationContext().getResources();
        TypedArray currencyFlags = r.obtainTypedArray(R.array.currency_flags);
        String[] currencyShortNames = r.getStringArray(R.array.currencies_short);
        String[] currencyLongNames = r.getStringArray(R.array.currencies_long);
        return ArraysToCurrenciesMapper.convertToCurrencies(currencyFlags, currencyShortNames, currencyLongNames);
    }
}
