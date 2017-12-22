package com.petrovdevelopment.paytmcurrencyconverter.presentation.usecases;

import android.support.annotation.NonNull;

import com.petrovdevelopment.paytmcurrencyconverter.domain.models.ExchangeRatesResponse;
import com.petrovdevelopment.paytmcurrencyconverter.domain.usecases.SynchronousUseCase;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.viewmodels.CurrenciesWithTimestamp;
import com.petrovdevelopment.paytmcurrencyconverter.domain.models.Currency;

import java.util.List;
import java.util.Map;

/**
 * Created by Andrey on 2017-12-21.
 * Coding is fun!
 */

public class PresentationUseCaseFactory {

    @NonNull
    public static SynchronousUseCase<CurrenciesWithTimestamp> convertExchangeRateToCurrenciesUseCase (ExchangeRatesResponse exchangeRatesResponse, Map<String, Currency> currencyLookUp, double amount) {
        return new ConvertExchangeRateToCurrenciesUseCase(exchangeRatesResponse, currencyLookUp, amount);
    }

    @NonNull
    public static SynchronousUseCase<Map<String, Currency>> createCurrenciesMapUseCase(List<Currency> currencies) {
        return new CreateCurrenciesMapUseCase(currencies);
    }

    @NonNull
    public static SynchronousUseCase<Void> updateCurrencyAmountsUseCase(List<Currency> listCurrencies, double amount) {
        return new UpdateCurrencyAmountsUseCase(listCurrencies, amount);
    }

}
