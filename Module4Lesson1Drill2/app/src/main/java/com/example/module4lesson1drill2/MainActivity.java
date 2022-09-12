package com.example.module4lesson1drill2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

        registerForContextMenu(img1);
        registerForContextMenu(img2);
        registerForContextMenu(img3);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.chocolate_cake_img){
            getMenuInflater().inflate(R.menu.image1_menu,menu);
        }

        else if (v.getId() == R.id.vanilla_cake_img){
            getMenuInflater().inflate(R.menu.image2_menu,menu);
        }

        else if (v.getId() == R.id.rainbow_cake_img){
            getMenuInflater().inflate(R.menu.image3_menu,menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.image_1){
            explainTv.setText(getString(R.string.chocolate_cake_explain));
            return  true;
        }

        else if (item.getItemId() == R.id.image_2){
            explainTv.setText(getString(R.string.vanilla_cake_explain));
            return  true;
        }

        else if (item.getItemId() == R.id.image_3){
            explainTv.setText(getString(R.string.rainbow_cake_explain));
            return  true;
        }
        return super.onContextItemSelected(item);
    }

}