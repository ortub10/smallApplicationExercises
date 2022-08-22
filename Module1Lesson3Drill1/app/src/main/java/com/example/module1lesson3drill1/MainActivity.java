package com.example.module1lesson3drill1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;


public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    ImageView dogImg;
    int firstWidth, firstHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dogImg = findViewById(R.id.dog_img);
        firstWidth = dogImg.getLayoutParams().width;
        firstHeight = dogImg.getLayoutParams().height;
        SwitchCompat disappear_sw = findViewById(R.id.switch_dog);
        disappear_sw.setOnCheckedChangeListener((compoundButton, b) ->
                dogImg.setVisibility(b? View.VISIBLE:View.INVISIBLE));

        SeekBar transparencySb = findViewById(R.id.transparency_seekbar);
        SeekBar enlargementSb = findViewById(R.id.enlargement_seekbar);
        transparencySb.setMax(255);
        transparencySb.setProgress(255);
        enlargementSb.setProgress(100);
        transparencySb.setOnSeekBarChangeListener(this);
        enlargementSb.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (seekBar.getId() == R.id.transparency_seekbar){
            dogImg.getDrawable().setAlpha(i);
        }

        else if (seekBar.getId() == R.id.enlargement_seekbar){
            dogImg.getLayoutParams().width = i*firstHeight/100;
            dogImg.getLayoutParams().height = i*firstHeight/100;
            dogImg.requestLayout();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}