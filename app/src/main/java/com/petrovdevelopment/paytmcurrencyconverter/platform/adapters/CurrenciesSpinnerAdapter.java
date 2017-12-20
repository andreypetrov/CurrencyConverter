package com.petrovdevelopment.paytmcurrencyconverter.platform.adapters;

import android.database.DataSetObserver;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.petrovdevelopment.paytmcurrencyconverter.R;
import com.petrovdevelopment.paytmcurrencyconverter.platform.viewmodels.CurrencyVM;

import java.util.List;

/**
 * Created by Andrey on 2017-12-19.
 */

public class CurrenciesSpinnerAdapter extends BaseAdapter {
    private List<CurrencyVM> currencyVMList;


    public CurrenciesSpinnerAdapter(List<CurrencyVM> currencyVMList) {
        if (currencyVMList == null) throw new IllegalArgumentException("view model list cannot be null");
        this.currencyVMList = currencyVMList;
    }

    @Override
    public int getCount() {
        return currencyVMList.size();
    }

    @Override
    public Object getItem(int i) {
        return currencyVMList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) view = createView(viewGroup);
        onBindViewHolder((ViewHolder) view.getTag(),i);
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        CurrencyVM currencyVM = currencyVMList.get(position);
        holder.flag.setImageDrawable(currencyVM.flagResourceId);
        holder.shortName.setText(currencyVM.shortName);
    }

    View createView(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.currency_spinner_view, viewGroup, false);
        view.setTag(new ViewHolder(view));
        return view;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView flag;
        public TextView shortName;

        public ViewHolder(View itemView) {
            super(itemView);
            flag = itemView.findViewById(R.id.flag);
            shortName = itemView.findViewById(R.id.shortName);
        }
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
