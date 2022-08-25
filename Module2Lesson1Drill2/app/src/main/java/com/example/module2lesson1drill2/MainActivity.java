package com.example.module2lesson1drill2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final int CALL_PERMISSION_REQUEST = 1;
    TextView phoneNumberTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumberTv = findViewById(R.id.phone_number_tv);
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
        Button star = findViewById(R.id.btn_star);
        Button ladder = findViewById(R.id.btn_ladder);

        ImageButton backspace = findViewById(R.id.backspace_btn);
        backspace.setOnClickListener(view->{
            String currentNumber = phoneNumberTv.getText().toString();
            if (!currentNumber.equals("")){
                String afterDelete = currentNumber.substring(0,currentNumber.length()-1);
                phoneNumberTv.setText(afterDelete);
            }
        });

        backspace.setOnLongClickListener(view -> {
            phoneNumberTv.setText("");
            return false;
        });

        ImageButton call = findViewById(R.id.call_btn);

        call.setOnClickListener(view->{
            if (Build.VERSION.SDK_INT >=23){
                int hasCallPermission =  checkSelfPermission(Manifest.permission.CALL_PHONE);
                if (hasCallPermission == PackageManager.PERMISSION_GRANTED){
                    callPhone();
                }
                else {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE},CALL_PERMISSION_REQUEST);
                }
            }

            else {
                callPhone();
            }
        });

        WriteNumber writeNumber = new WriteNumber();

        btn0.setOnClickListener(writeNumber);
        btn1.setOnClickListener(writeNumber);
        btn2.setOnClickListener(writeNumber);
        btn3.setOnClickListener(writeNumber);
        btn4.setOnClickListener(writeNumber);
        btn5.setOnClickListener(writeNumber);
        btn6.setOnClickListener(writeNumber);
        btn7.setOnClickListener(writeNumber);
        btn8.setOnClickListener(writeNumber);
        btn9.setOnClickListener(writeNumber);
        star.setOnClickListener(writeNumber);
        ladder.setOnClickListener(writeNumber);


    }

    public void callPhone(){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+phoneNumberTv.getText().toString()));
        phoneNumberTv.setText("");
        startActivity(intent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PERMISSION_REQUEST){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                callPhone();
            }
        }
    }

    private class WriteNumber implements View.OnClickListener{
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {
            phoneNumberTv.setText(phoneNumberTv.getText().toString()+((Button)view).getText().toString());
        }
    }
}

