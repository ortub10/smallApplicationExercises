package com.example.module5lesson3drill1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.MessageFormat;
import java.util.List;

public class SoccerMatchResultAdapter extends RecyclerView.Adapter<SoccerMatchResultAdapter.SoccerMatchResultViewHolder> {

    final private List<SoccerMatchResult> soccerMatchResults;

    public SoccerMatchResultAdapter(List<SoccerMatchResult> soccerMatchResults) {
        this.soccerMatchResults = soccerMatchResults;
    }

    public static class SoccerMatchResultViewHolder extends RecyclerView.ViewHolder{
        ImageView flagCountry1Iv;
        TextView scoreCountry1Tv;
        ImageView flagCountry2Iv;
        TextView scoreCountry2Tv;

        public SoccerMatchResultViewHolder(@NonNull View itemView) {
            super(itemView);
            flagCountry1Iv = itemView.findViewById(R.id.flag_country1);
            scoreCountry1Tv = itemView.findViewById(R.id.score_country1);
            flagCountry2Iv = itemView.findViewById(R.id.flag_country2);
            scoreCountry2Tv = itemView.findViewById(R.id.score_country2);
        }
    }

    @NonNull
    @Override
    public SoccerMatchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.soccer_match_result_layout,parent,false);
        return new SoccerMatchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SoccerMatchResultViewHolder holder, int position) {
        SoccerMatchResult soccerMatchResult =  soccerMatchResults.get(position);
        holder.flagCountry1Iv.setImageResource(soccerMatchResult.getFlagCountry1ResId());
        holder.scoreCountry1Tv.setText(MessageFormat.format("{0}", soccerMatchResult.getScoreCountry1()));
        holder.flagCountry2Iv.setImageResource(soccerMatchResult.getFlagCountry2ResId());
        holder.scoreCountry2Tv.setText(MessageFormat.format("{0}", soccerMatchResult.getScoreCountry2()));
    }

    @Override
    public int getItemCount() {
        return soccerMatchResults.size();
    }
}
