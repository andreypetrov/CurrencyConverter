package com.petrovdevelopment.paytmcurrencyconverter.presentation.usecases;


import android.content.res.Resources;
import android.content.res.TypedArray;

import com.petrovdevelopment.paytmcurrencyconverter.R;
import com.petrovdevelopment.paytmcurrencyconverter.domain.gateways.LocalGateway;
import com.petrovdevelopment.paytmcurrencyconverter.domain.usecases.SynchronousUseCase;
import com.petrovdevelopment.paytmcurrencyconverter.platform.services.gateways.XmlLocalGateway;
import com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels.CurrencyVM;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 2017-12-18.
 */

public class LocalCurrenciesUseCase implements SynchronousUseCase<List<CurrencyVM>> {
    private LocalGateway localGateway;

    public LocalCurrenciesUseCase(LocalGateway localGateway) {
        this.localGateway = localGateway;
    }

    public List<CurrencyVM> execute() {
        return localGateway.getCurrencies();
    }
}
