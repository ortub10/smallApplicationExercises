package com.example.module3lesson1drill1;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NamesActivity extends AppCompatActivity {
    SharedPreferences sp;
    int numberOfNames;
    @SuppressLint("ApplySharedPref")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.names_layout);
        sp = getSharedPreferences("details",MODE_PRIVATE);
        numberOfNames = sp.getInt("number_names",0);
        LinearLayout linearLayoutNames = findViewById(R.id.linear_layout_names);
        TextView textView = findViewById(R.id.name_output);
        textView.setText(sp.getString("name",""));

        EditText nameEt = findViewById(R.id.add_name_et);

        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view->{
            String name = nameEt.getText().toString();
            if (!name.equals("")){
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name"+numberOfNames,name);
                numberOfNames++;
                editor.putInt("number_names",numberOfNames);
                nameEt.setText("");
                editor.commit();

            }
        });


        Button showBtn = findViewById(R.id.show_btn);
        showBtn.setOnClickListener(view->{
            linearLayoutNames.removeAllViews();
            for (int i = 0; i<numberOfNames; i++){
                TextView nameTv = new TextView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                nameTv.setLayoutParams(layoutParams);
                nameTv.setTextSize(30);
                nameTv.setText(sp.getString("name"+i,""));
                nameTv.setGravity(Gravity.CENTER);
                linearLayoutNames.addView(nameTv);
            }
        });
    }

}
