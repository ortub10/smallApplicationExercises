package com.example.module3lesson1drill1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    EditText nameEt;
    SeekBar sb;
    SwitchCompat sw;
    ImageView imageView;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textSb = findViewById(R.id.text_sb);
        sp = getSharedPreferences("details",MODE_PRIVATE);
        nameEt = findViewById(R.id.name_et);
        sb = findViewById(R.id.sb_number);
        sw = findViewById(R.id.sw);
        imageView = findViewById(R.id.image);
        nameEt.setText(sp.getString("name",""));
        sb.setProgress(sp.getInt("progress",0));
        textSb.setText(MessageFormat.format("{0}", sb.getProgress()));
        sw.setChecked(sp.getBoolean("sw",false));

        try {
            FileInputStream fis = openFileInput("images");
            bitmap = BitmapFactory.decodeStream(fis);
            imageView.setImageBitmap(bitmap);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textSb.setText(MessageFormat.format("{0}", i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button takPicBtn = findViewById(R.id.pic_btn);
        takPicBtn.setOnClickListener(view->{
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            imageResult.launch(intent);
        });

        Button continueBtn = findViewById(R.id.continue_btn);
        continueBtn.setOnClickListener(view->{
            Intent intent = new Intent(this, NamesActivity.class);
            startActivity(intent);
        });

    }

    ActivityResultLauncher<Intent> imageResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
        if (result != null && result.getResultCode() == RESULT_OK){
            if (result.getData() != null){
                bitmap = (Bitmap) result.getData().getExtras().get("data");
                imageView.setImageBitmap(bitmap);
            }
        }
    });



    @SuppressLint("ApplySharedPref")
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",nameEt.getText().toString());
        editor.putInt("progress",sb.getProgress());
        editor.putBoolean("sw",sw.isChecked());
        editor.commit();

        try {
            FileOutputStream fos = openFileOutput("images",MODE_PRIVATE);
            if (bitmap != null){
                bitmap.compress(Bitmap.CompressFormat.JPEG,50,fos);
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}