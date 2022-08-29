package com.example.module2lesson2drill1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button writeDetailsBtn = findViewById(R.id.start_write_details);
        writeDetailsBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, FillDetailsActivity.class);
            startActivity(intent);

        });
    }
}