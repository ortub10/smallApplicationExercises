package com.example.module1lesson2drill4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = new FrameLayout(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        frameLayout.setLayoutParams(layoutParams);

        ImageView imageView = new ImageView(this);
        FrameLayout.LayoutParams layoutParamsImg = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParamsImg);
        imageView.setImageResource(R.drawable.cat);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        frameLayout.addView(imageView);

        TextView textView = new TextView(this);
        FrameLayout.LayoutParams layoutParamsTv = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
       float scale = getResources().getDisplayMetrics().density;
        layoutParamsTv.setMargins(0,(int)(20 * scale),0,0);
        layoutParamsTv.setMarginStart(20);
        textView.setLayoutParams(layoutParamsTv);
        textView.setText(getResources().getString(R.string.cat));
        textView.setTextSize(40);
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setTypeface(null,Typeface.BOLD);
        frameLayout.addView(textView);
        setContentView(frameLayout);


    }
}