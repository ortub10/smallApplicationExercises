package com.example.module5lesson2drill2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gallery = findViewById(R.id.gallery_view);

        ArrayList<CountryCard> countryCards = new ArrayList<>();
        countryCards.add(new CountryCard("Australia",R.drawable.flag_australia));
        countryCards.add(new CountryCard("Brazil",R.drawable.flag_brazil));
        countryCards.add(new CountryCard("China",R.drawable.flag_china));
        countryCards.add(new CountryCard("France",R.drawable.flag_france));
        countryCards.add(new CountryCard("Israel",R.drawable.flag_israel));
        countryCards.add(new CountryCard("Nederland",R.drawable.flag_nederland));
        countryCards.add(new CountryCard("Spain",R.drawable.flag_spain));
        countryCards.add(new CountryCard("Turkish",R.drawable.flag_turkish));
        countryCards.add(new CountryCard("UK",R.drawable.flag_uk));

        countryCards.add(new CountryCard("Australia",R.drawable.flag_australia));
        countryCards.add(new CountryCard("Brazil",R.drawable.flag_brazil));
        countryCards.add(new CountryCard("China",R.drawable.flag_china));
        countryCards.add(new CountryCard("France",R.drawable.flag_france));
        countryCards.add(new CountryCard("Israel",R.drawable.flag_israel));
        countryCards.add(new CountryCard("Nederland",R.drawable.flag_nederland));
        countryCards.add(new CountryCard("Spain",R.drawable.flag_spain));
        countryCards.add(new CountryCard("Turkish",R.drawable.flag_turkish));
        countryCards.add(new CountryCard("UK",R.drawable.flag_uk));

        countryCards.add(new CountryCard("Australia",R.drawable.flag_australia));
        countryCards.add(new CountryCard("Brazil",R.drawable.flag_brazil));
        countryCards.add(new CountryCard("China",R.drawable.flag_china));
        countryCards.add(new CountryCard("France",R.drawable.flag_france));
        countryCards.add(new CountryCard("Israel",R.drawable.flag_israel));
        countryCards.add(new CountryCard("Nederland",R.drawable.flag_nederland));
        countryCards.add(new CountryCard("Spain",R.drawable.flag_spain));
        countryCards.add(new CountryCard("Turkish",R.drawable.flag_turkish));
        countryCards.add(new CountryCard("UK",R.drawable.flag_uk));


        CountryCardAdapter countryCardAdapter = new CountryCardAdapter(countryCards);
        gallery.setAdapter(countryCardAdapter);

        gallery.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(this, CountryActivity.class);
            intent.putExtra("country_name",countryCards.get(i).getName());
            intent.putExtra("country_id",countryCards.get(i).getIdFlag());
            startActivity(intent);
        });
    }
}