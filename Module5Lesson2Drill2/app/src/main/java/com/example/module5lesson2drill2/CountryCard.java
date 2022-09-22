package com.example.module5lesson2drill2;

public class CountryCard {
    private String name;
    private int idFlag;

    public CountryCard(String name, int idFlag) {
        this.name = name;
        this.idFlag = idFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdFlag() {
        return idFlag;
    }

    public void setIdFlag(int idFlag) {
        this.idFlag = idFlag;
    }
}
