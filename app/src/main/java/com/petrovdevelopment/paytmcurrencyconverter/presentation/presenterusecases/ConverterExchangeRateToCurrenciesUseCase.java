package com.petrovdevelopment.paytmcurrencyconverter.presentation.presenterusecases;

import com.petrovdevelopment.paytmcurrencyconverter.domain.usecases.SynchronousUseCase;
import com.petrovdevelopment.paytmcurrencyconverter.domain.utils.CurrencyUtils;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.models.exceptions.ExchangeRateResponseException;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.models.ExchangeRatesResponse;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.viewmodels.Currency;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.utils.PresenterUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey on 2017-12-20.
 */

public class ConverterExchangeRateToCurrenciesUseCase implements SynchronousUseCase<List<Currency>> {
    private final ExchangeRatesResponse exchangeRatesResponse;
    private final Map<String, Currency> currencyLookUp;
    private final double amount;

    public ConverterExchangeRateToCurrenciesUseCase(ExchangeRatesResponse exchangeRatesResponse, Map<String, Currency> currencyLookUp, double amount) {
        this.exchangeRatesResponse = exchangeRatesResponse;
        this.currencyLookUp = currencyLookUp;
        this.amount = amount;
    }

    @Override
    public List<Currency> execute() throws ExchangeRateResponseException {
        return createCurrenciesList(exchangeRatesResponse);
    }

    private List<Currency> createCurrenciesList(ExchangeRatesResponse response) throws ExchangeRateResponseException {
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
        return currencies;
    }

}
