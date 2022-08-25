package com.example.module2lesson1drill1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nameEt = findViewById(R.id.name_edittext);
        EditText ageEt = findViewById(R.id.age_edittext);
        EditText phoneEt = findViewById(R.id.phone_edittext);

        Button sendBtn = findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(view->{
            Intent intent = new Intent(this,DetailsActivity.class);
            intent.putExtra("name",nameEt.getText().toString());
            intent.putExtra("age",ageEt.getText().toString());
            intent.putExtra("phone",phoneEt.getText().toString());
            startActivity(intent);
        });


    }
}