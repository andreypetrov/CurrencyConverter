package com.petrovdevelopment.paytmcurrencyconverter.presentation.usecases;

import com.petrovdevelopment.paytmcurrencyconverter.domain.usecases.SynchronousUseCase;
import com.petrovdevelopment.paytmcurrencyconverter.domain.models.Currency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey on 2017-12-21.
 * Coding is fun!
 */

public class CreateCurrenciesMapUseCase implements SynchronousUseCase<Map<String, Currency>> {
    private final List<Currency> currencies;

    CreateCurrenciesMapUseCase(List<Currency> currencies) {
        this.currencies  = currencies;
    }

    @Override
    public Map<String, Currency> execute() {
        return createCurrencyMap(currencies);
    }

    private Map<String, Currency> createCurrencyMap(List<Currency> listCurrencies) {
        Map<String, Currency> map = new HashMap<>();
        for (Currency currency : listCurrencies) map.put(currency.shortName, currency);
        return map;
    }
}
