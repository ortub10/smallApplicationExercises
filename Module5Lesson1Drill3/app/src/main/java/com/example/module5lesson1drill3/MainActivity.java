package com.example.module5lesson1drill3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        ArrayList<Map<String,Object>> dataDog = new ArrayList<>();
        for (int i = 0; i < dogs.size(); i++ ){
            HashMap<String,Object> dogMap = new HashMap<>();
            dogMap.put("name",dogs.get(i).getName());
            dogMap.put("age",dogs.get(i).getAge());
            dogMap.put("color",dogs.get(i).getColor());
            dataDog.add(dogMap);
        }
        String [] from = {"name","age","color"};
        int [] to = {R.id.name_output,R.id.age_output, R.id.color_output};
        ListView listViewDogs = findViewById(R.id.list_dog);
        SimpleAdapter simpleAdapterDogs = new SimpleAdapter(this,dataDog,R.layout.dog_layout,from,to);


        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            String name = nameEt.getText().toString();
            String age = ageEt.getText().toString();
            String color = colorEt.getText().toString();
            if (!name.equals("") && !age.equals("") &&!color.equals("") ){
                int ageNumber = Integer.parseInt(age);
                Dog dog = new Dog(name,ageNumber,color);
                dogs.add(dog);
                HashMap<String,Object> dogMap = new HashMap<>();
                dogMap.put("name",dog.getName());
                dogMap.put("age",dog.getAge());
                dogMap.put("color",dog.getColor());
                dataDog.add(dogMap);
                simpleAdapterDogs.notifyDataSetChanged();
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
                listViewDogs.setAdapter(simpleAdapterDogs)
        );
    }
}