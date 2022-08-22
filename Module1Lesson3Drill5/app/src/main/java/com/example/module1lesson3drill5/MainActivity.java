package com.example.module1lesson3drill5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int currentImgNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] images = new int[9];
        images[0] = R.drawable.flag_israel;
        images[1] = R.drawable.flag_australia;
        images[2] = R.drawable.flag_brazil;
        images[3] = R.drawable.flag_brithin;
        images[4] = R.drawable.flag_france;
        images[5] = R.drawable.flag_arab;
        images[6] = R.drawable.flag_holand;
        images[7] = R.drawable.flag_turkish;
        images[8] = R.drawable.flag_spain;
        ImageView mainImg = findViewById(R.id.naim_img);
        ImageButton leftImgBtn = findViewById(R.id.left_arrow);
        ImageButton rightImgBtn = findViewById(R.id.right_arrow);
        currentImgNumber = 0;
        leftImgBtn.setOnClickListener(view -> {
            currentImgNumber--;
            if (currentImgNumber < 0) currentImgNumber = images.length-1;
            mainImg.setImageResource(images[currentImgNumber]);
        });

        rightImgBtn.setOnClickListener(view -> {
            currentImgNumber++;
            if (currentImgNumber >= images.length) currentImgNumber = 0;
            mainImg.setImageResource(images[currentImgNumber]);
        });

    }
}