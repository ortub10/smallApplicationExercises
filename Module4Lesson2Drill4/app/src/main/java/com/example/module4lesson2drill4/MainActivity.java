package com.example.module4lesson2drill4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView startTimeTv, endTimeTv;
    boolean cancel = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startTimeTv = findViewById(R.id.start_time_output);
        endTimeTv = findViewById(R.id.end_time_output);

        Button startTimeBtn = findViewById(R.id.start_time_btn);
        startTimeBtn.setOnClickListener(new ChooseTime());
        Button endTimeBtn = findViewById(R.id.end_time_btn);
        endTimeBtn.setOnClickListener(new ChooseTime());

        Button progressSecondsBtn =findViewById(R.id.progress_seconds_btn);
        progressSecondsBtn.setOnClickListener(view -> {
            if (!startTimeTv.getText().toString().equals("") && !endTimeTv.getText().toString().equals("") ){
                String [] startTimeNumbers = startTimeTv.getText().toString().split(":");
                String [] endTimeNumbers = endTimeTv.getText().toString().split(":");
                int hoursSpace = Integer.parseInt(endTimeNumbers[0]) - Integer.parseInt(startTimeNumbers[0]);
                int minutesSpace = Integer.parseInt(endTimeNumbers[1]) - Integer.parseInt(startTimeNumbers[1]);
                cancel = false;
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setTitle("Please Wait");
                progressDialog.setMessage("Seconds run");
                progressDialog.setMax((hoursSpace*60+minutesSpace)*60);
                progressDialog.setCancelable(false);
                progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "cancel", (dialogInterface, i) -> cancel = true);
                progressDialog.show();


                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        while (!cancel){
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            progressDialog.incrementProgressBy(1);
                            if (progressDialog.getProgress() == progressDialog.getMax())
                                cancel = true;
                        }
                        progressDialog.dismiss();
                    }
                }.start();
            }
            else {
                Toast.makeText(this, "Please fill the times", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private class ChooseTime implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minutes = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, (timePicker, i, i1) -> {
                if (view.getId() == R.id.start_time_btn){
                    startTimeTv.setText(MessageFormat.format("{0}:{1}",i,i1));
                }
                else if (view.getId() == R.id.end_time_btn){
                    endTimeTv.setText(MessageFormat.format("{0}:{1}",i,i1));
                }
            },hour,minutes,true);
            timePickerDialog.show();
        }
    }
}