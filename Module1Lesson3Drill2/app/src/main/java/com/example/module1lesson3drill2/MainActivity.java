package com.example.module1lesson3drill2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableLayout tableLayout = findViewById(R.id.table_layout);
        EditText numberEt = findViewById(R.id.number_edittext);
        ToggleButton disappearTb = findViewById(R.id.toggle_disappear);
        disappearTb.setOnCheckedChangeListener((compoundButton, b) -> {
            String numberStr = numberEt.getText().toString();
            if (numberStr.matches("[0-9]+")){
                int number = Integer.parseInt(numberStr);
                tableLayout.setColumnCollapsed(number-1,b);
            }
        });
    }
}