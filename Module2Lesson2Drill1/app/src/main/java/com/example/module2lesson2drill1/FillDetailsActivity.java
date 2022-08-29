package com.example.module2lesson2drill1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FillDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fill_details_activity);
        EditText fullNameEt = findViewById(R.id.name_et);
        EditText numberPhoneEt = findViewById(R.id.phone_et);
        EditText emailAddressEt = findViewById(R.id.email_et);
        EditText homeAddressEt = findViewById(R.id.home_et);
        EditText webEt = findViewById(R.id.web_et);

        Button finishBtn = findViewById(R.id.finish_btn);
        finishBtn.setOnClickListener(view->{
            String fullName = fullNameEt.getText().toString();
            String numberPhone = numberPhoneEt.getText().toString();
            String emailAddress = emailAddressEt.getText().toString();
            String homeAddress = homeAddressEt.getText().toString();
            String web = webEt.getText().toString();

            if (fullName.equals("") || numberPhone.equals("") || emailAddress.equals("")||homeAddress.equals("") || web.equals("")){
                Toast.makeText(this, getResources().getString(R.string.please_enter_all_details), Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(this, DetailsActivity.class);
                intent.putExtra("name",fullName);
                intent.putExtra("phone",numberPhone);
                intent.putExtra("email",emailAddress);
                intent.putExtra("home",homeAddress);
                intent.putExtra("web",web);
                startActivity(intent);
            }
        });
    }
}
