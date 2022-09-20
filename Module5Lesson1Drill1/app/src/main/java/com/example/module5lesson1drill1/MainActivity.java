package com.example.module5lesson1drill1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Dog> dogs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            FileInputStream fis = openFileInput("dogs");
            ObjectInputStream ois = new ObjectInputStream(fis);
            dogs = (ArrayList<Dog>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        EditText nameEt = findViewById(R.id.name_et_input);
        EditText ageEt = findViewById(R.id.age_et_input);
        EditText colorEt = findViewById(R.id.color_et_input);

        ListView listViewDogs = findViewById(R.id.list_dog);
        ArrayAdapter<Dog> arrayAdapterDogs = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,dogs);


        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            String name = nameEt.getText().toString();
            String age = ageEt.getText().toString();
            String color = colorEt.getText().toString();
            if (!name.equals("") && !age.equals("") &&!color.equals("") ){
                int ageNumber = Integer.parseInt(age);
                Dog dog = new Dog(name,ageNumber,color);
                dogs.add(dog);
                arrayAdapterDogs.notifyDataSetChanged();
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
        showBtn.setOnClickListener(view ->
                listViewDogs.setAdapter(arrayAdapterDogs)
        );
    }
}