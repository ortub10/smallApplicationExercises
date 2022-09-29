package com.example.module5lesson3drill1;

public class SoccerMatchResult {
    private int flagCountry1ResId;
    private int scoreCountry1;
    private int flagCountry2ResId;
    private int scoreCountry2;

    public SoccerMatchResult(int flagCountry1ResId, int scoreCountry1, int flagCountry2ResId, int scoreCountry2) {
        this.flagCountry1ResId = flagCountry1ResId;
        this.scoreCountry1 = scoreCountry1;
        this.flagCountry2ResId = flagCountry2ResId;
        this.scoreCountry2 = scoreCountry2;
    }

    public int getFlagCountry1ResId() {
        return flagCountry1ResId;
    }

    public void setFlagCountry1ResId(int flagCountry1ResId) {
        this.flagCountry1ResId = flagCountry1ResId;
    }

    public int getScoreCountry1() {
        return scoreCountry1;
    }

    public void setScoreCountry1(int scoreCountry1) {
        this.scoreCountry1 = scoreCountry1;
    }

    public int getFlagCountry2ResId() {
        return flagCountry2ResId;
    }

    public void setFlagCountry2ResId(int flagCountry2ResId) {
        this.flagCountry2ResId = flagCountry2ResId;
    }

    public int getScoreCountry2() {
        return scoreCountry2;
    }

    public void setScoreCountry2(int scoreCountry2) {
        this.scoreCountry2 = scoreCountry2;
    }
}

