package com.petrovdevelopment.paytmcurrencyconverter.platform.ui.viewmodel;

import android.os.Bundle;

/**
 * Created by Andrey on 2017-12-21.
 */

public class MainActivityState {
    private static final String AMOUNT_KEY = "amount_key";
    private static final String CURRENT_SELECTOR_POSITION_KEY = "current_selector_position_key";

    private String amount;
    private int currentSelectorPosition;

    public MainActivityState(String amount, int currentSelectorPosition) {
        this.amount = amount;
        this.currentSelectorPosition = currentSelectorPosition;
    }

    public static MainActivityState createFromBundle(Bundle savedInstanceState) {
        String amount = savedInstanceState.getString(AMOUNT_KEY);
        int currentSelectorCurrencyPosition = savedInstanceState.getInt(CURRENT_SELECTOR_POSITION_KEY);
        MainActivityState mainActivityState = new MainActivityState(amount, currentSelectorCurrencyPosition);
        return mainActivityState;
    }

    public void storeToBundle(Bundle bundle) {
        bundle.putString(AMOUNT_KEY, amount);
        bundle.putInt(CURRENT_SELECTOR_POSITION_KEY, currentSelectorPosition);
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getCurrentSelectorPosition() {
        return currentSelectorPosition;
    }

    public void setCurrentSelectorPosition(int currentSelectorPosition) {
        this.currentSelectorPosition = currentSelectorPosition;
    }
}
