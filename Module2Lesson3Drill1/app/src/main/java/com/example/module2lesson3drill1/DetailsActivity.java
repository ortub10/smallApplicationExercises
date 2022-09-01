package com.example.module2lesson3drill1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        TextView nameTv = findViewById(R.id.name_textview);
        TextView ageTv = findViewById(R.id.age_textview);
        TextView phoneTv = findViewById(R.id.phone_textview);
        String phoneNumber = getIntent().getStringExtra("phone");
        nameTv.setText(getIntent().getStringExtra("name"));
        ageTv.setText(getIntent().getStringExtra("age"));
        phoneTv.setText(phoneNumber);

        phoneTv.setOnClickListener(view->{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+phoneNumber));
            startActivity(intent);
        });

        ImageView imgDetails = findViewById(R.id.img_details);
        imgDetails.setImageBitmap(getIntent().getParcelableExtra("img"));
        Button backBtn = findViewById(R.id.back_btn);
        backBtn.setOnClickListener(view-> finish());

    }
}
