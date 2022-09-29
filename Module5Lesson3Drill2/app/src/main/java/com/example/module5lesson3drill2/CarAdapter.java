package com.example.module5lesson3drill2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.MessageFormat;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private final List<Car> cars;
    private CarListener carListener;

    public CarAdapter(List<Car> cars) {
        this.cars = cars;
    }

    interface CarListener{
        void onClick(int position, double km);
    }

    public void setListener(CarListener carListener){
        this.carListener = carListener;
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {
        TextView kmTv;
        TextView fuelTv;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            kmTv = itemView.findViewById(R.id.km_tv);
            fuelTv = itemView.findViewById(R.id.fuel_tv);
            EditText kmEt = itemView.findViewById(R.id.km_et);
            Button driveBtn = itemView.findViewById(R.id.drive_btn);

            driveBtn.setOnClickListener(view -> {
                double km = Double.parseDouble(kmEt.getText().toString());
                if (carListener!=null)
                    carListener.onClick(getAdapterPosition(),km);
                kmEt.setText("");

            });
        }
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_layout,parent,false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = cars.get(position);
        holder.kmTv.setText(MessageFormat.format("{0}", car.getKm()));
        holder.fuelTv.setText(MessageFormat.format("{0}", car.getFuel()));
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }
}
