package com.example.module5lesson2drill1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listViewCar = findViewById(R.id.cars_list);

        List<Car> cars = new ArrayList<>();

        cars.add(new Car(10000,20,4));
        cars.add(new Car(20000,40,12));
        cars.add(new Car(17000,60,6));
        cars.add(new Car(100000,20,10));
        cars.add(new Car(19000,35,7.5));
        cars.add(new Car(10000,20,4));
        cars.add(new Car(20000,40,12));
        cars.add(new Car(17000,60,6));
        cars.add(new Car(100000,20,10));
        cars.add(new Car(19000,35,7.5));
        cars.add(new Car(10000,20,4));
        cars.add(new Car(20000,40,12));
        cars.add(new Car(17000,60,6));
        cars.add(new Car(100000,20,10));
        cars.add(new Car(19000,35,7.5));

        CarAdapter carAdapter = new CarAdapter(this,cars);
        listViewCar.setAdapter(carAdapter);
    }
}