package com.example.module4lesson2drill3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView volumeOutputMainTv = findViewById(R.id.volume_output_main);
        TextView muteOutputMainTv = findViewById(R.id.mute_output_main);
        Button volumeBtn = findViewById(R.id.volume_btn);
        volumeBtn.setOnClickListener(view -> {
            View viewVoice = getLayoutInflater().inflate(R.layout.dialog_voice_view,null);
            SeekBar volumeSb =  viewVoice.findViewById(R.id.volume_sb);
            TextView volumeOutputTv = viewVoice.findViewById(R.id.volume_output);
            CheckBox muteCb =  viewVoice.findViewById(R.id.mute_cb);
            String muteOutputMain = muteOutputMainTv.getText().toString();
            String volumeOutputMain = volumeOutputMainTv.getText().toString();
            if (!muteOutputMain.equals("") && !volumeOutputMain.equals("")){
                volumeSb.setProgress(Integer.parseInt(volumeOutputMain));
                volumeOutputTv.setText(volumeOutputMain);
                muteCb.setChecked(muteOutputMain.equals("Quiet"));
                volumeSb.setEnabled(!muteOutputMain.equals("Quiet"));
            }
            volumeSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    volumeOutputTv.setText(MessageFormat.format("{0}",i));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            muteCb.setOnCheckedChangeListener((compoundButton, b) ->
                    volumeSb.setEnabled(!b));
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(viewVoice);
            builder.setPositiveButton("Finish", (dialogInterface, i) -> {
                volumeOutputMainTv.setText(volumeOutputTv.getText().toString());
                muteOutputMainTv.setText(muteCb.isChecked()?"Quiet":"Not quiet");
            });
            builder.show();
        });
    }
}