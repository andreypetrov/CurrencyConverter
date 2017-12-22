package com.petrovdevelopment.paytmcurrencyconverter.platform.ui.viewmodel;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Contains state which is only very specific to the view. The actual responses are kept on presenter level
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

    @NonNull
    public static MainActivityState createFromBundle(Bundle savedInstanceState) {
        String amount = savedInstanceState.getString(AMOUNT_KEY);
        int currentSelectorCurrencyPosition = savedInstanceState.getInt(CURRENT_SELECTOR_POSITION_KEY);
        return new MainActivityState(amount, currentSelectorCurrencyPosition);
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
