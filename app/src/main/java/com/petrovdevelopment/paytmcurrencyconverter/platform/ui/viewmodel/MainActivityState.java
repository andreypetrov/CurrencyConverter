package com.petrovdevelopment.paytmcurrencyconverter.platform.ui.viewmodel;

/**
 * Created by Andrey on 2017-12-21.
 */

public class MainActivityState {
    private static final String AMOUNT_KEY = "amount_key";
    private static final String CURRENT_SELECTOR_CURRENCY_POSITION_KEY = "current_selector_currency_position_key";

    private String amount;
    private int currentSelectorCurrencyPosition;

    public MainActivityState(String amount, int currentSelectorCurrencyPosition) {
        this.amount = amount;
        this.currentSelectorCurrencyPosition = currentSelectorCurrencyPosition;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getCurrentSelectorCurrencyPosition() {
        return currentSelectorCurrencyPosition;
    }

    public void setCurrentSelectorCurrencyPosition(int currentSelectorCurrencyPosition) {
        this.currentSelectorCurrencyPosition = currentSelectorCurrencyPosition;
    }
}
