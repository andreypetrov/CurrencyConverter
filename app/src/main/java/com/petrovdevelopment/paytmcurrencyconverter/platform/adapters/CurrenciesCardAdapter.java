package com.petrovdevelopment.paytmcurrencyconverter.platform.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.petrovdevelopment.paytmcurrencyconverter.R;
import com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels.CurrencyVM;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.MainPresenter;

/**
 * Created by Andrey on 2017-12-19.
 */

public class CurrenciesCardAdapter extends RecyclerView.Adapter<CurrenciesCardAdapter.ViewHolder> {
    private MainPresenter presenter;

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
        CurrencyVM currencyVM = presenter.getListCurrency(position);
        holder.flagView.setImageDrawable(currencyVM.flag);
        holder.shortNameView.setText(currencyVM.shortName);
        holder.longNameView.setText(currencyVM.longName);
        holder.exchangeRateView.setText(currencyVM.exchangeRate);
        holder.amountView.setText(currencyVM.amount);
    }

    @Override
    public int getItemCount() {
        return presenter.getListCurrenciesCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView flagView;
        TextView shortNameView;
        TextView longNameView;
        TextView exchangeRateView;
        TextView amountView;

        public ViewHolder(View itemView) {
            super(itemView);
            flagView = itemView.findViewById(R.id.flag);
            shortNameView = itemView.findViewById(R.id.shortName);
            longNameView = itemView.findViewById(R.id.longName);
            exchangeRateView = itemView.findViewById(R.id.exchangeRate);
            amountView = itemView.findViewById(R.id.amount);
        }
    }
}
