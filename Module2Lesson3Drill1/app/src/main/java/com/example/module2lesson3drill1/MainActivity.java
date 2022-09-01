package com.example.module2lesson3drill1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imgMain;
    Bitmap bitmap;

    ActivityResultLauncher<Intent> takePicture = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result !=null && result.getResultCode() == RESULT_OK){
                if (result.getData()!=null){
                    bitmap = (Bitmap)result.getData().getExtras().get("data");
                    imgMain.setImageBitmap(bitmap);
                }
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nameEt = findViewById(R.id.name_edittext);
        EditText ageEt = findViewById(R.id.age_edittext);
        EditText phoneEt = findViewById(R.id.phone_edittext);
        imgMain = findViewById(R.id.img_main);
        ImageButton CameraImageButton = findViewById(R.id.camera_img_btn);
        CameraImageButton.setOnClickListener(view->{
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            takePicture.launch(intent);

        });
        Button sendBtn = findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(view->{
            Intent intent = new Intent(this,DetailsActivity.class);
            intent.putExtra("name",nameEt.getText().toString());
            intent.putExtra("age",ageEt.getText().toString());
            intent.putExtra("phone",phoneEt.getText().toString());
            intent.putExtra("img",bitmap);
            startActivity(intent);
        });


    }
}