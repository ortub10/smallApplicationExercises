package com.example.module1lesson1drill2;

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

        EditText firstNumberEt = findViewById(R.id.number1_et);
        EditText secondNumberEt = findViewById(R.id.number2_et);
        TextView resultTv = findViewById(R.id.result_tv);
        Button finishBtn =findViewById(R.id.finish_btn);
        finishBtn.setOnClickListener(view ->{
            if (!firstNumberEt.getText().toString().equals("") &&!secondNumberEt.getText().toString().equals("")){
                int number1 = Integer.parseInt(firstNumberEt.getText().toString());
                int number2 = Integer.parseInt(secondNumberEt.getText().toString());
                resultTv.setText(number1+number2+"");
            }
            else {
                resultTv.setText("");
            }
        });
    }
}