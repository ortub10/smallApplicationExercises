package com.example.module1lesson3drill4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;
SeekBar redSb, blueSb, greenSb,alphaSb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.linear_layout);
        redSb = findViewById(R.id.red_seekbar);
        blueSb = findViewById(R.id.blue_seekbar);
        greenSb = findViewById(R.id.green_seekbar);
        alphaSb = findViewById(R.id.alpha_seekbar);

        makeColors makeColors = new makeColors();
        redSb.setOnSeekBarChangeListener(makeColors);
        blueSb.setOnSeekBarChangeListener(makeColors);
        greenSb.setOnSeekBarChangeListener(makeColors);
        alphaSb.setOnSeekBarChangeListener(makeColors);

    }

    class makeColors  implements SeekBar.OnSeekBarChangeListener{
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            int color = Color.argb(alphaSb.getProgress(),redSb.getProgress(),greenSb.getProgress(),blueSb.getProgress());
            linearLayout.setBackgroundColor(color);

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}