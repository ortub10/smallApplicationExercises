package com.example.module1lesson3drill3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout relativeLayout = findViewById(R.id.relative_layout);

        RadioGroup radioGroup = findViewById(R.id.radio_group);

        radioGroup.setOnCheckedChangeListener((radioGroup1, i) -> {
            switch (i){
                case R.id.red_radio:
                    relativeLayout.setBackgroundResource(R.color.red);
                    break;
                case R.id.blue_radio:
                    relativeLayout.setBackgroundResource(R.color.blue);
                    break;
                case R.id.yellow_radio:
                    relativeLayout.setBackgroundResource(R.color.yellow);
                    break;
                case R.id.pink_radio:
                    relativeLayout.setBackgroundResource(R.color.pink);
                    break;
                case R.id.orange_radio:
                    relativeLayout.setBackgroundResource(R.color.orange);
                    break;


            }
        });
    }
}