package com.example.module5lesson1drill4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String [] countries = getResources().getStringArray(R.array.countries_array);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.auto_complete);
        ArrayAdapter<String> arrayAdapter  = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,countries);
        autoCompleteTextView.setAdapter(arrayAdapter);

        MultiAutoCompleteTextView multiAutoCompleteTextView = findViewById(R.id.multi_complete);
        multiAutoCompleteTextView.setAdapter(arrayAdapter);
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}