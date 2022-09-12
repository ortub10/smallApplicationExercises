package com.example.module4lesson1drill3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
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

        MenuClick menuClick = new MenuClick();
        img1.setOnClickListener(menuClick);
        img2.setOnClickListener(menuClick);
        img3.setOnClickListener(menuClick);


    }


    private class MenuClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            view.startActionMode(new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    if (view.getId() == R.id.chocolate_cake_img){
                        getMenuInflater().inflate(R.menu.image1_menu,menu);
                        return true;
                    }

                    else if (view.getId() == R.id.vanilla_cake_img){
                        getMenuInflater().inflate(R.menu.image2_menu,menu);
                        return true;
                    }

                    else if (view.getId() == R.id.rainbow_cake_img){
                        getMenuInflater().inflate(R.menu.image3_menu,menu);
                        return true;
                    }
                    return false;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
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
                }

                @Override
                public void onDestroyActionMode(ActionMode actionMode) {

                }
            });
        }
    }

}