package com.petrovdevelopment.paytmcurrencyconverter.platform.adapters;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.petrovdevelopment.paytmcurrencyconverter.R;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.MainPresenter;
import com.petrovdevelopment.paytmcurrencyconverter.presentation.outer.CurrencySelectorItemView;

/**
 * Created by Andrey on 2017-12-19.
 */

public class CurrenciesSpinnerAdapter extends BaseAdapter {
    private MainPresenter presenter;
    private LayoutInflater layoutInflater; //TODO use for picture


    public CurrenciesSpinnerAdapter(MainPresenter presenter, LayoutInflater layoutInflater) {
        this.presenter = presenter;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return presenter.getSelectorCurrenciesCount();
    }

    @Override
    public Object getItem(int i) {
        return presenter.getSelectorCurrency(i);
    }

    @Override
    public long getItemId(int i) {
        return presenter.getSelectorCurrencyId(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) view = createView(viewGroup);
        onBindViewHolder((ViewHolder) view.getTag(),i);
        return view;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        presenter.configureSelectorCurrencyItem(holder, position);
    }

    View createView(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.currency_spinner_view, viewGroup, false);
        view.setTag(new ViewHolder(view));
        return view;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements CurrencySelectorItemView {
        public ImageView flagView;
        public TextView shortNameView;

        public ViewHolder(View itemView) {
            super(itemView);
            flagView = itemView.findViewById(R.id.flag);
            shortNameView = itemView.findViewById(R.id.shortName);
        }

        @Override
        public void displayFlag(Drawable flag) {
            flagView.setImageDrawable(flag);
        }

        @Override
        public void displayShortName(String shortName) {
            shortNameView.setText(shortName);
        }
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
