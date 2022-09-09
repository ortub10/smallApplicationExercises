package com.example.module3lesson1drill3;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView dogImage;
    Bitmap bitmap;
    ArrayList<Dog> dogs;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            FileInputStream fis = openFileInput("dogs");
            ObjectInputStream ois = new ObjectInputStream(fis);
            dogs =  (ArrayList<Dog>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        EditText nameEt = findViewById(R.id.name_et_input);
        EditText ageEt = findViewById(R.id.age_et_input);
        EditText colorEt = findViewById(R.id.color_et_input);
        dogImage = findViewById(R.id.pic_dog);

        Button takePicBtn = findViewById(R.id.pic_btn);
        takePicBtn.setOnClickListener(view->{
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            imageResult.launch(intent);
        });

        LinearLayout dogsLayout = findViewById(R.id.dogs_layout);

        Button addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(view -> {
            String name = nameEt.getText().toString();
            String age = ageEt.getText().toString();
            String color = colorEt.getText().toString();
            if (!name.equals("") && !age.equals("") &&!color.equals("")  && bitmap!=null){
                int ageNumber = Integer.parseInt(age);
                Dog dog = new Dog(name,ageNumber,color,bitmap);
                if (dogs == null){
                    dogs = new ArrayList<>();
                }
                dogs.add(dog);
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
                bitmap = null;
                dogImage.setImageBitmap(null);
            }
        });

        Button showBtn = findViewById(R.id.show_btn);
        showBtn.setOnClickListener(view -> {
            dogsLayout.removeAllViews();
            if (dogs != null){

                for (int i = 0; i < dogs.size(); i++){
                    Dog dogOut = dogs.get(i);
                    LinearLayout dogLayout = new LinearLayout(this);
                    LinearLayout.LayoutParams dogLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dogLayoutParams.topMargin = 10;
                    dogLayout.setPadding(10,10,10,10);
                    dogLayout.setLayoutParams(dogLayoutParams);

                    ImageView dogImageToShow = new ImageView(this);
                    LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
                    imageLayoutParams.weight = 1;
                    dogImageToShow.setLayoutParams(imageLayoutParams);
                    dogImageToShow.setImageBitmap(dogOut.getBitmap());
                    dogLayout.addView(dogImageToShow);

                    TextView dogTv = new TextView(this);
                    LinearLayout.LayoutParams textTvLayoutParams = new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT);
                    textTvLayoutParams.weight = 3;
                    dogTv.setLayoutParams(textTvLayoutParams);
                    dogTv.setGravity(Gravity.CENTER);
                    dogTv.setTextSize(30);
                    dogTv.setText(MessageFormat.format("{0} {1} {2}", dogOut.getName(), dogOut.getAge(), dogOut.getColor()));
                    dogLayout.addView(dogTv);

                    dogsLayout.addView(dogLayout);
                }
            }
        });
    }

    ActivityResultLauncher<Intent> imageResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result !=null && result.getResultCode() == RESULT_OK){
            if (result.getData() != null){
                bitmap = (Bitmap) result.getData().getExtras().get("data");
                dogImage.setImageBitmap(bitmap);
             }
        }
    });
}