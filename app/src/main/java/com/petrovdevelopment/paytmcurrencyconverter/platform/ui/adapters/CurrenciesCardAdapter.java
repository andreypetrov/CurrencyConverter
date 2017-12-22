package com.petrovdevelopment.paytmcurrencyconverter.platform.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.petrovdevelopment.paytmcurrencyconverter.R;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.viewmodels.Currency;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.presenters.MainPresenter;

/**
 * Created by Andrey on 2017-12-19.
 */

public class CurrenciesCardAdapter extends RecyclerView.Adapter<CurrenciesCardAdapter.ViewHolder> {
    private final MainPresenter presenter;

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
        Currency currency = presenter.getListCurrency(position);
        holder.flagView.setImageDrawable(currency.flag);
        holder.shortNameView.setText(currency.shortName);
        holder.longNameView.setText(currency.longName);
        holder.exchangeRateView.setText(currency.exchangeRate);
        holder.amountView.setText(currency.amount);
    }

    @Override
    public int getItemCount() {
        return presenter.getListCurrenciesCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final ImageView flagView;
        final TextView shortNameView;
        final TextView longNameView;
        final TextView exchangeRateView;
        final TextView amountView;

        public ViewHolder(View itemView) {
            super(itemView);
            flagView = itemView.findViewById(R.id.flag);
            shortNameView = itemView.findViewById(R.id.shortName);
            longNameView = itemView.findViewById(R.id.longName);
            exchangeRateView = itemView.findViewById(R.id.exchangeRate);
            amountView = itemView.findViewById(R.id.amountView);
        }
    }
}
