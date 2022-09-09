package com.example.module3lesson1drill4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import androidx.exifinterface.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    final int WRITE_PERMISSION_REQUEST = 1;
    ImageView dogImage;
    Bitmap bitmap;
    ArrayList<Dog> dogs;
    File file;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT>=23){
            int hasWritePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (hasWritePermission == PackageManager.PERMISSION_DENIED){
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_PERMISSION_REQUEST);
            }
        }

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
            file = new File(Environment.getExternalStorageDirectory(),"jj/dog"+(dogs != null? dogs.size() : 0)+".jpg");
            Uri imageUri = FileProvider.getUriForFile(this,
                    "com.example.module3lesson1drill4.provider", file);

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
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
                Dog dog = new Dog(name,ageNumber,color,file.getAbsolutePath());
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
                    LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(0, 200);
                    imageLayoutParams.weight = 1;
                    dogImageToShow.setOnClickListener(view1 -> {
                        Intent intent = new Intent(this, FullImageActivity.class);
                        intent.putExtra("full",dogOut.getPhotoPath());
                        startActivity(intent);
                    });
                    dogImageToShow.setLayoutParams(imageLayoutParams);

                    dogImageToShow.setImageBitmap(rotate(BitmapFactory.decodeFile(dogOut.getPhotoPath()),dogOut.getPhotoPath()));
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
            Bitmap tempBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            bitmap = rotate(tempBitmap, file.getAbsolutePath());
            dogImage.setImageBitmap(bitmap);

        }
    });

    private Bitmap rotate(Bitmap tempBitmap, String fileStr){
        ExifInterface exifInterface = null;
        try {
            exifInterface = new ExifInterface(fileStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (exifInterface!=null){
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_UNDEFINED);
            Matrix matrix = new Matrix();
            switch (orientation){
                case ExifInterface.ORIENTATION_ROTATE_90:
                    matrix.setRotate(90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    matrix.setRotate(180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    matrix.setRotate(270);
                    break;
            }

            return Bitmap.createBitmap(tempBitmap, 0, 0, tempBitmap.getWidth(), tempBitmap.getHeight(), matrix, true);
        }
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_PERMISSION_REQUEST){
            if (grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Can't take picture", Toast.LENGTH_SHORT).show();
            }
        }
    }
}