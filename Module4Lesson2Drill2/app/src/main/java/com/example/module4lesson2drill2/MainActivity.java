package com.example.module4lesson2drill2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int checked = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String [] colors = {"Green","Red","Blue","Yellow"};
        String [] days = {"Sunday","Monday","Tuesday","Wednesday", "Thursday","Friday","Saturday"};
        boolean [] daysChosen = {false,false,false,false,false,false,false};
        String [] gender = {"Male", "Female", "Other"};
        LinearLayout linearLayout = findViewById(R.id.linear_layout);

        Button chooseBackgroundColorBtn = findViewById(R.id.background_color_btn);
        chooseBackgroundColorBtn.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Choose background color");
            builder.setItems(colors, (dialogInterface, i) -> {
                switch (colors[i]){
                    case "Green":
                        linearLayout.setBackgroundColor(Color.GREEN);
                        break;
                    case "Red":
                        linearLayout.setBackgroundColor(Color.RED);
                        break;
                    case "Blue":
                        linearLayout.setBackgroundColor(Color.BLUE);
                        break;
                    case "Yellow":
                        linearLayout.setBackgroundColor(Color.YELLOW);
                        break;
                }
            });
            builder.show();
        });

        Button chooseDaysBtn = findViewById(R.id.choose_days_btn);
        TextView daysTv = findViewById(R.id.days_output);
        ArrayList<String> daysList = new ArrayList<>();
        chooseDaysBtn.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Choose days");
            builder.setMultiChoiceItems(days, daysChosen, (dialogInterface, i, b) -> {
                daysChosen[i] = b;
                if (b) daysList.add(days[i]);
                else daysList.remove(days[i]);
            });
            builder.setPositiveButton("Finish", (dialogInterface, i) ->
                    daysTv.setText(daysList.toString())
            );
            builder.setCancelable(false);
            builder.show();
        });

        Button chooseGenderBtn = findViewById(R.id.choose_gender_btn);
        TextView genderTv = findViewById(R.id.gender_output);
        chooseGenderBtn.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Choose gender");
            builder.setSingleChoiceItems(gender, checked, (dialogInterface, i) -> {
                checked = i;
                genderTv.setText(gender[i]);
            });
            builder.setNeutralButton("Finish", (dialogInterface, i) ->
                dialogInterface.dismiss()
            );
            builder.setCancelable(false);
            builder.show();
        });
    }
}