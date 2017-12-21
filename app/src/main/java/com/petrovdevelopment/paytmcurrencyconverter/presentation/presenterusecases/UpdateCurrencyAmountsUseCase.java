package com.petrovdevelopment.paytmcurrencyconverter.presentation.presenterusecases;

import com.petrovdevelopment.paytmcurrencyconverter.domain.usecases.SynchronousUseCase;
import com.petrovdevelopment.paytmcurrencyconverter.domain.utils.CurrencyUtils;
import com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels.Currency;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.utils.PresenterUtils;

import java.util.List;
import java.util.Locale;

/**
 * TODO consider a calculated field in the Currency view model, which calculates dynamically new amount based on base amount and exchange rate.
 * Created by Andrey on 2017-12-21.
 */

public class UpdateCurrencyAmountsUseCase implements SynchronousUseCase<Void> {

    private final List<Currency> listCurrencies;
    private final double amount;

    public UpdateCurrencyAmountsUseCase(List<Currency> listCurrencies, double amount) {
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
