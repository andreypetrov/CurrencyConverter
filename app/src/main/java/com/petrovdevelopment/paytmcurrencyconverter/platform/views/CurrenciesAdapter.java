package com.petrovdevelopment.paytmcurrencyconverter.platform.views;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.petrovdevelopment.paytmcurrencyconverter.R;
import com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels.CurrencyVM;

import org.w3c.dom.Text;

import java.util.List;

/**
 *
 * Created by Andrey on 2017-12-19.
 */

class CurrenciesAdapter extends RecyclerView.Adapter<CurrenciesAdapter.ViewHolder> {
    List<CurrencyVM> currencyVMList;

    public CurrenciesAdapter(List<CurrencyVM> currencyVMList) {
        if (currencyVMList == null) throw new IllegalArgumentException("view model list cannot be null");
        this.currencyVMList = currencyVMList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_card_view, parent, false);
        return new ViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CurrencyVM currencyVM = currencyVMList.get(position);
        holder.flag.setImageResource(currencyVM.flagResourceId);
        holder.shortName.setText(currencyVM.shortName);
        holder.longName.setText(currencyVM.longName);
        holder.exchangeRate.setText(currencyVM.exchangeRate);
        holder.amount.setText(currencyVM.amount);
    }

    @Override
    public int getItemCount() {
        return currencyVMList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView flag;
        public TextView shortName;
        public TextView longName;
        public TextView exchangeRate;
        public TextView amount;


        public ViewHolder(View itemView) {
            super(itemView);
            flag = itemView.findViewById(R.id.flag);
            shortName = itemView.findViewById(R.id.shortName);
            longName = itemView.findViewById(R.id.longName);
            exchangeRate = itemView.findViewById(R.id.exchangeRate);
            amount = itemView.findViewById(R.id.amount);
        }
    }
}
