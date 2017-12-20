package com.petrovdevelopment.paytmcurrencyconverter.platform.adapters;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.petrovdevelopment.paytmcurrencyconverter.R;
import com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels.CurrencyVM;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.MainPresenter;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.CurrencyListItemView;

/**
 * Created by Andrey on 2017-12-19.
 */

public class CurrenciesCardAdapter extends RecyclerView.Adapter<CurrenciesCardAdapter.ViewHolder> {
    MainPresenter presenter;


    public CurrenciesCardAdapter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_card_view, parent, false);
        return new ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        presenter.configureListCurrencyCell(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getListCurrenciesCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements CurrencyListItemView{
        public ImageView flagView;
        public TextView shortNameView;
        public TextView longNameView;
        public TextView exchangeRateView;
        public TextView amountView;


        public ViewHolder(View itemView) {
            super(itemView);
            flagView = itemView.findViewById(R.id.flag);
            shortNameView = itemView.findViewById(R.id.shortName);
            longNameView = itemView.findViewById(R.id.longName);
            exchangeRateView = itemView.findViewById(R.id.exchangeRate);
            amountView = itemView.findViewById(R.id.amount);
        }

        @Override
        public void displayFlag(Drawable flag) {
            flagView.setImageDrawable(flag);
        }

        @Override
        public void displayShortName(String shortName) {
            shortNameView.setText(shortName);
        }

        @Override
        public void displayExchangeRate(String exchangeRate) {
            exchangeRateView.setText(exchangeRate);
        }

        @Override
        public void displayAmount(String amount) {
            amountView.setText(amount);
        }

    }
}
