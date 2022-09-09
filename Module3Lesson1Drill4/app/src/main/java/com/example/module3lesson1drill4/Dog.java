package com.example.module3lesson1drill4;

import java.io.Serializable;

public class Dog implements Serializable {
    private String name;
    private int age;
    private String color;
    private String photoPath;

    public Dog(String name, int age, String color, String photoPath) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.photoPath = photoPath;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

}