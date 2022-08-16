package com.example.module1lesson1drill1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText firstNameEt = findViewById(R.id.first_name_et);
        EditText lastNameEt = findViewById(R.id.last_name_et);
        TextView fullNameTv = findViewById(R.id.full_name_tv);
        Button finishBtn = findViewById(R.id.finish_btn);


        finishBtn.setOnClickListener(view ->{
            String firstName = firstNameEt.getText().toString();
            String lastName = lastNameEt.getText().toString();
            fullNameTv.setText(firstName+" "+lastName);

        });
    }
}