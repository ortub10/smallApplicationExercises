package com.example.module3lesson1drill2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Dog> dogs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            FileInputStream fis = openFileInput("dogs");
            ObjectInputStream ois = new ObjectInputStream(fis);
            dogs =  (ArrayList<Dog>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        EditText nameEt = findViewById(R.id.name_et_input);
        EditText ageEt = findViewById(R.id.age_et_input);
        EditText colorEt = findViewById(R.id.color_et_input);

        LinearLayout dogsLayout = findViewById(R.id.dogs_layout);

        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            String name = nameEt.getText().toString();
            String age = ageEt.getText().toString();
            String color = colorEt.getText().toString();
            if (!name.equals("") && !age.equals("") &&!color.equals("") ){
                int ageNumber = Integer.parseInt(age);
                Dog dog = new Dog(name,ageNumber,color);
                if (dogs == null){
                    dogs = new ArrayList<>();
                }
                dogs.add(dog);
                try {
                    FileOutputStream fos = openFileOutput("dogs",MODE_PRIVATE);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(dogs);
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                nameEt.setText("");
                ageEt.setText("");
                colorEt.setText("");
            }
        });

        Button showBtn = findViewById(R.id.show_btn);
        showBtn.setOnClickListener(view -> {
            dogsLayout.removeAllViews();
            if (dogs != null){

                for (int i = 0; i < dogs.size(); i++){
                    Dog dogOut = dogs.get(i);
                    TextView dogTv = new TextView(this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dogTv.setLayoutParams(layoutParams);
                    dogTv.setGravity(Gravity.CENTER);
                    dogTv.setTextSize(30);
                    dogTv.setText(MessageFormat.format("{0} {1} {2}", dogOut.getName(), dogOut.getAge(), dogOut.getColor()));
                    dogsLayout.addView(dogTv);
                }
            }
        });
    }
}