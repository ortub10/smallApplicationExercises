package com.example.module2lesson3drill2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    ActivityResultLauncher<Intent> imageResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result !=null && result.getResultCode() == RESULT_OK) {
                if (result.getData() != null) {
                    Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    });
    ActivityResultLauncher<Intent> speakResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result !=null && result.getResultCode() == RESULT_OK){
            if (result.getData()!=null){
                ArrayList<String> results = result.getData().getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (results.get(0).equals(getString(R.string.camera))){
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        imageResult.launch(intent);
                }
                else if(results.get(0).equals(getString(R.string.browser))){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://google.com"));
                    startActivity(intent);
                }
                
                else{
                    Toast.makeText(this, getString(R.string.not_recognized), Toast.LENGTH_SHORT).show();
                }
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.img);
        Button speakBtn = findViewById(R.id.speak_btn);
        speakBtn.setOnClickListener(view->{
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,getString(R.string.say_camera_or_browser));
            speakResult.launch(intent);

        });
    }
}