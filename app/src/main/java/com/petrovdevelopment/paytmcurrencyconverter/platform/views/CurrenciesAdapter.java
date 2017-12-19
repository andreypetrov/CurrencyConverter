package com.petrovdevelopment.paytmcurrencyconverter.platform.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels.CurrencyVM;

import java.util.List;

/**
 * Created by Andrey on 2017-12-19.
 */

class CurrenciesAdapter extends RecyclerView.Adapter<CurrenciesAdapter.ViewHolder> {
    public CurrenciesAdapter(List<CurrencyVM> currencyVMList) {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView currencyShortName;
        public TextView currencyLongName;
        public ImageView currencyFlag;


        public ViewHolder(View itemView) {
            super(itemView);
//            itemView.findv
        }
    }
}
