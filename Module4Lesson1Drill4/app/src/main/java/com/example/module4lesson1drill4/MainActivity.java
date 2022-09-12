package com.example.module4lesson1drill4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView explainTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img1  = findViewById(R.id.chocolate_cake_img);
        ImageView img2  = findViewById(R.id.vanilla_cake_img);
        ImageView img3  = findViewById(R.id.rainbow_cake_img);
        explainTv = findViewById(R.id.explain_image_chosen);

        PopupMenuClick popupMenuClick = new PopupMenuClick();
        img1.setOnClickListener(popupMenuClick);
        img2.setOnClickListener(popupMenuClick);
        img3.setOnClickListener(popupMenuClick);
    }

    private class PopupMenuClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
            if (view.getId() == R.id.chocolate_cake_img){
                getMenuInflater().inflate(R.menu.image1_menu,popupMenu.getMenu());
            }

            else if (view.getId() == R.id.vanilla_cake_img){
                getMenuInflater().inflate(R.menu.image2_menu,popupMenu.getMenu());
            }

            else if (view.getId() == R.id.rainbow_cake_img){
                getMenuInflater().inflate(R.menu.image3_menu,popupMenu.getMenu());
            }
            popupMenu.setOnMenuItemClickListener(menuItem ->{
                if (menuItem.getItemId() == R.id.image_1){
                    explainTv.setText(getString(R.string.chocolate_cake_explain));
                    return true;
                }

                else if (menuItem.getItemId() == R.id.image_2){
                    explainTv.setText(getString(R.string.vanilla_cake_explain));
                    return true;
                }

                else if (menuItem.getItemId() == R.id.image_3){
                    explainTv.setText(getString(R.string.rainbow_cake_explain));
                    return true;
                }

                return false;
            });
            popupMenu.show();
        }
    }
}