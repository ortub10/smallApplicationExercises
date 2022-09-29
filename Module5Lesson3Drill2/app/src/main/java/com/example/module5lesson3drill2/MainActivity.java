package com.example.module5lesson3drill2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewCar = findViewById(R.id.car_list);

        recyclerViewCar.setHasFixedSize(true);
        recyclerViewCar.setLayoutManager(new LinearLayoutManager(this));

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

        CarAdapter carAdapter = new CarAdapter(cars);
        carAdapter.setListener((position,km) -> {
            Car car = cars.get(position);
            if (!car.drive(km)){
                Toast.makeText(this, "Low fuel", Toast.LENGTH_SHORT).show();
            }
            carAdapter.notifyItemChanged(position);
        });

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT){
                    cars.remove(viewHolder.getAdapterPosition());
                    carAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                }

                else if (direction == ItemTouchHelper.RIGHT){
                    Car car = cars.get(viewHolder.getAdapterPosition());
                    car.setFuel(car.getFuel()+50);
                    carAdapter.notifyItemChanged(viewHolder.getAdapterPosition());

                }

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerViewCar);
        recyclerViewCar.setAdapter(carAdapter);


    }
}