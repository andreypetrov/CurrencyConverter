package com.petrovdevelopment.paytmcurrencyconverter.presentation.usecases;

import com.petrovdevelopment.paytmcurrencyconverter.domain.usecases.SynchronousUseCase;
import com.petrovdevelopment.paytmcurrencyconverter.domain.utils.CurrencyUtils;
import com.petrovdevelopment.paytmcurrencyconverter.domain.models.exceptions.ExchangeRateResponseException;
import com.petrovdevelopment.paytmcurrencyconverter.domain.models.ExchangeRatesResponse;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.viewmodels.CurrenciesWithTimestamp;
import com.petrovdevelopment.paytmcurrencyconverter.domain.models.Currency;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.utils.PresenterUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey on 2017-12-20.
 */

public class ConvertExchangeRateToCurrenciesUseCase implements SynchronousUseCase<CurrenciesWithTimestamp> {
    private final ExchangeRatesResponse exchangeRatesResponse;
    private final Map<String, Currency> currencyLookUp;
    private final double amount;

    ConvertExchangeRateToCurrenciesUseCase(ExchangeRatesResponse exchangeRatesResponse, Map<String, Currency> currencyLookUp, double amount) {
        this.exchangeRatesResponse = exchangeRatesResponse;
        this.currencyLookUp = currencyLookUp;
        this.amount = amount;
    }

    @Override
    public CurrenciesWithTimestamp execute() throws ExchangeRateResponseException {
        return createCurrenciesList(exchangeRatesResponse);
    }

    private CurrenciesWithTimestamp createCurrenciesList(ExchangeRatesResponse response) throws ExchangeRateResponseException {
        if (response.getError() != null) throw new ExchangeRateResponseException(response.getError());
        List<Currency> currencies = new ArrayList<>();
        if (response.getRates() != null) {
            for (Map.Entry<String, Double> entry : response.getRates().entrySet()) {
                Currency currency = currencyLookUp.get(entry.getKey());
                if (currency == null) currency = new Currency(entry.getKey()); //just in case we don't have the currency locally. It shouldn't happen
                currency.exchangeRate = String.valueOf(entry.getValue());
                currency.amount = PresenterUtils.toString(CurrencyUtils.convert(amount, entry.getValue()));
                currencies.add(currency);
            }
        }
        return new CurrenciesWithTimestamp(response.getDate(), currencies);
    }

}
