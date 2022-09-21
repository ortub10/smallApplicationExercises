package com.example.module5lesson2drill1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.List;

public class CarAdapter extends BaseAdapter implements View.OnClickListener {

    final private Context context;
    final private List<Car> cars;

    public CarAdapter(Context context, List<Car> cars) {
        this.context = context;
        this.cars = cars;
    }

    @Override
    public int getCount() {
        return cars.size();
    }

    @Override
    public Object getItem(int i) {
        return cars.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.car_layout,viewGroup,false);
//            view = LayoutInflater.from(context).inflate(R.layout.car_layout,viewGroup,false);
        }

        Car car = cars.get(i);

        Button driveBtn = view.findViewById(R.id.drive_btn);
        driveBtn.setTag(i);
        driveBtn.setOnClickListener(this);
        TextView kmTv = view.findViewById(R.id.km_tv);
        TextView fuelTv = view.findViewById(R.id.fuel_tv);
        kmTv.setText(MessageFormat.format("{0}",car.getKm()));
        fuelTv.setText(MessageFormat.format("{0}",car.getFuel()));
        return view;
    }

    @Override
    public void onClick(View view) {

        LinearLayout root = (LinearLayout) view.getParent();
        EditText editText = root.findViewById(R.id.km_et);

        int position = (Integer) view.getTag();

        Car car = cars.get(position);
        double km = Double.parseDouble(editText.getText().toString());
        car.drive(km);
        notifyDataSetChanged();
        editText.setText("");
    }
}
