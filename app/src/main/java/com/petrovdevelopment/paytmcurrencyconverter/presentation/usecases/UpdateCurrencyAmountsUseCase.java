package com.petrovdevelopment.paytmcurrencyconverter.presentation.usecases;

import com.petrovdevelopment.paytmcurrencyconverter.domain.usecases.SynchronousUseCase;
import com.petrovdevelopment.paytmcurrencyconverter.domain.utils.CurrencyUtils;
import com.petrovdevelopment.paytmcurrencyconverter.domain.models.Currency;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.utils.PresenterUtils;

import java.util.List;

/**
 * Created by Andrey on 2017-12-21.
 * Coding is fun!
 */

public class UpdateCurrencyAmountsUseCase implements SynchronousUseCase<Void> {

    private final List<Currency> listCurrencies;
    private final double amount;

    UpdateCurrencyAmountsUseCase(List<Currency> listCurrencies, double amount) {
        this.listCurrencies = listCurrencies;
        this.amount = amount;
    }

    @Override
    public Void execute() {
        for (Currency currency : listCurrencies) {
            currency.amount = PresenterUtils.toString(CurrencyUtils.convert(amount, Double.valueOf(currency.exchangeRate)));
        }
        return null;
    }
}
