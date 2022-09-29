package com.example.module5lesson3drill1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewSoccer = findViewById(R.id.soccer_game);
        recyclerViewSoccer.setHasFixedSize(true);

        recyclerViewSoccer.setLayoutManager(new GridLayoutManager(this, 2));

        List<SoccerMatchResult> soccerMatchResults = new ArrayList<>();
        soccerMatchResults.add(new SoccerMatchResult(R.drawable.flag_australia,3,R.drawable.flag_brazil,4));
        soccerMatchResults.add(new SoccerMatchResult(R.drawable.flag_china,2,R.drawable.flag_france,3));
        soccerMatchResults.add(new SoccerMatchResult(R.drawable.flag_nederland,1,R.drawable.flag_israel,4));
        soccerMatchResults.add(new SoccerMatchResult(R.drawable.flag_spain,5,R.drawable.flag_turkish,3));
        soccerMatchResults.add(new SoccerMatchResult(R.drawable.flag_uk,3,R.drawable.flag_brazil,1));
        soccerMatchResults.add(new SoccerMatchResult(R.drawable.flag_israel,6,R.drawable.flag_turkish,0));

        soccerMatchResults.add(new SoccerMatchResult(R.drawable.flag_israel,1,R.drawable.flag_brazil,4));
        soccerMatchResults.add(new SoccerMatchResult(R.drawable.flag_uk,2,R.drawable.flag_china,0));
        soccerMatchResults.add(new SoccerMatchResult(R.drawable.flag_australia,1,R.drawable.flag_israel,4));
        soccerMatchResults.add(new SoccerMatchResult(R.drawable.flag_spain,5,R.drawable.flag_nederland,3));
        soccerMatchResults.add(new SoccerMatchResult(R.drawable.flag_uk,3,R.drawable.flag_brazil,1));
        soccerMatchResults.add(new SoccerMatchResult(R.drawable.flag_australia,3,R.drawable.flag_turkish,0));


        SoccerMatchResultAdapter soccerMatchResultAdapter=  new SoccerMatchResultAdapter(soccerMatchResults);
        recyclerViewSoccer.setAdapter(soccerMatchResultAdapter);
    }
}