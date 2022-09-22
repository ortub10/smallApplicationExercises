package com.example.module5lesson2drill2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CountryCardAdapter extends BaseAdapter {

    private ArrayList<CountryCard> countryCards;

    public CountryCardAdapter(ArrayList<CountryCard> countryCards) {
        this.countryCards = countryCards;
    }

    @Override
    public int getCount() {
        return countryCards.size();
    }

    @Override
    public Object getItem(int i) {
        return countryCards.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout,viewGroup,false);
        }

        CountryCard countryCard = countryCards.get(i);

        ImageView flagIv = view.findViewById(R.id.image_flag);
        TextView nameCountryTv = view.findViewById(R.id.name_country);

        flagIv.setImageResource(countryCard.getIdFlag());
        nameCountryTv.setText(countryCard.getName());

        return view;
    }
}
