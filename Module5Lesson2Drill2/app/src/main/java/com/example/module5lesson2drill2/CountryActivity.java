package com.example.module5lesson2drill2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CountryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_activity);

        TextView textView = findViewById(R.id.name_country_output);
        ImageView imageView = findViewById(R.id.image_flag_output);

        textView.setText(getIntent().getStringExtra("country_name"));
        imageView.setImageResource(getIntent().getIntExtra("country_id",0));
    }
}
