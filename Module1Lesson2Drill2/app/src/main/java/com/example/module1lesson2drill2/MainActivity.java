package com.example.module1lesson2drill2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView screenTv;
    int result;
    String action = "";
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screenTv = findViewById(R.id.screen_tv);
        Button btn0 = findViewById(R.id.btn_0);
        Button btn1 = findViewById(R.id.btn_1);
        Button btn2 = findViewById(R.id.btn_2);
        Button btn3 = findViewById(R.id.btn_3);
        Button btn4 = findViewById(R.id.btn_4);
        Button btn5 = findViewById(R.id.btn_5);
        Button btn6 = findViewById(R.id.btn_6);
        Button btn7 = findViewById(R.id.btn_7);
        Button btn8 = findViewById(R.id.btn_8);
        Button btn9 = findViewById(R.id.btn_9);

        Button btnPlus = findViewById(R.id.btn_plus);
        Button btnMinus = findViewById(R.id.btn_minus);
        Button btnDuplicate = findViewById(R.id.btn_duplicate);
        Button btnDivide = findViewById(R.id.btn_divide);

        Button btnEqual = findViewById(R.id.btn_equal);
        Button btnClear = findViewById(R.id.btn_clear);

        btn0.setOnClickListener(new getNumber());
        btn1.setOnClickListener(new getNumber());
        btn2.setOnClickListener(new getNumber());
        btn3.setOnClickListener(new getNumber());
        btn4.setOnClickListener(new getNumber());
        btn5.setOnClickListener(new getNumber());
        btn6.setOnClickListener(new getNumber());
        btn7.setOnClickListener(new getNumber());
        btn8.setOnClickListener(new getNumber());
        btn9.setOnClickListener(new getNumber());

        btnPlus.setOnClickListener(new action());
        btnMinus.setOnClickListener(new action());
        btnDuplicate.setOnClickListener(new action());
        btnDivide.setOnClickListener(new action());

        btnEqual.setOnClickListener(view ->{
            String textNumber = screenTv.getText().toString();
            if (!textNumber.equals("") && !action.equals("")){

                int currentNumber = Integer.parseInt(textNumber);
                switch (action){
                    case "+":
                        result+=currentNumber;
                        break;
                    case "-":
                        result-=currentNumber;
                        break;
                    case "X":
                        result*=currentNumber;
                        break;
                    case "/":
                        if (currentNumber==0)return;
                        result/=currentNumber;
                        break;
                }
                screenTv.setText(result+"");
            }
        });

        btnClear.setOnClickListener(view ->{
            screenTv.setText("");
            action = "";
        });
    }

    private class getNumber implements View.OnClickListener{
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {
            String number = ((Button)view).getText().toString();
            String prev = screenTv.getText().toString();
            screenTv.setText(prev+number);
        }
    }
    private class action implements View.OnClickListener{
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {
            String textNumber = screenTv.getText().toString();
            if (!textNumber.equals("")){
                result=Integer.parseInt(textNumber);
                screenTv.setText("");
                action = ((Button)view).getText().toString();
            }

        }
    }
}