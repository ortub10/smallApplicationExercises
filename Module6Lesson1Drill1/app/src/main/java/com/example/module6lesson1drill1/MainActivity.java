package com.example.module6lesson1drill1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.GridView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final  String[] links = {
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBVUhQD8_DlF4P8qA12BGjHpPBu2zpRki398QSBRxlmLEWJ4SIhM7kbSTi77_rWwXnzrM&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSf1antgPmFoL-Gnf5KLF4rX0cQkRfQRcoNyKpvalpjW9cd9pMatkuF6zSKoBBaroy3Lqc&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRejthea_VDzE5KlT4H-WZr8v-Bh_IVxFGlqRByY6SmJ8uJj9JieyZph1sclCKozytIezM&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQorS1oqmD0ZL06wgO4Vwfnk_nCQNXTLI0v_nSHITwNx-wgAhrZngTTiQIXgiUbYkRn1Xg&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSymfU-gWtOw6gV2Eb3aJmHFAcLAN1fF4uUoA&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcREiQ5cwXtOguY82DFyslT6Giww_sLyeCYiqA&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSsu3DlK-JzhDEQwgywLwnij8GFd2-vUSunlw&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQq62Yzwn-4ZaFHUlSlDxyjlnC2esGNPZQN2w&usqp=CAU",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4XqIOa7h6M13PXvENan1JoeA0YeYQI5zc1g&usqp=CAU"
    };

    ArrayList<Bitmap> bitmaps = new ArrayList<>();
    Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = findViewById(R.id.grid_view);

        ImageAdapter adapter = new ImageAdapter(bitmaps);
        gridView.setAdapter(adapter);

        new Thread(){
            @Override
            public void run() {
                super.run();
                for (String link : links) {
                    try {
                        URL url = new URL(link);
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.connect();
                        bitmaps.add(BitmapFactory.decodeStream(httpURLConnection.getInputStream()));
                        handler.post(adapter::notifyDataSetChanged);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();



    }
}