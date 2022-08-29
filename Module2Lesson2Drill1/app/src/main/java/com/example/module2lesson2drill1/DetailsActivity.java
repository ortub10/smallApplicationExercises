package com.example.module2lesson2drill1;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        TextView fullNameTv = findViewById(R.id.name_tv);
        TextView numberPhoneTv = findViewById(R.id.phone_tv);
        TextView emailTv = findViewById(R.id.email_tv);
        TextView homeTv = findViewById(R.id.home_tv);
        TextView webTv = findViewById(R.id.web_tv);

        fullNameTv.setText(getIntent().getStringExtra("name"));
        numberPhoneTv.setText(getIntent().getStringExtra("phone"));
        emailTv.setText(getIntent().getStringExtra("email"));
        homeTv.setText(getIntent().getStringExtra("home"));
        webTv.setText(getIntent().getStringExtra("web"));

        numberPhoneTv.setOnClickListener(view->{
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("tel:"+((TextView)view).getText().toString()));
            startActivity(intent);
        });

        emailTv.setOnClickListener(view->{
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL,new String[]{((TextView)view).getText().toString()});
            intent.putExtra(Intent.EXTRA_SUBJECT, "main mail");
            intent.putExtra(Intent.EXTRA_TEXT,"Welcome to company");
            intent.setType("text/html");
            startActivity(intent);
        });

        homeTv.setOnClickListener(view->{
            try {
                // Launch Waze to look for Hawaii:
                String url = "https://waze.com/ul?q="+((TextView)view).getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                // If Waze is not installed, open it in Google Play:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.waze"));
                startActivity(intent);
            }
        });
        webTv.setOnClickListener(view->{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+((TextView)view).getText().toString()));
            startActivity(intent);
        });
    }
}
