package com.example.module3lesson1drill3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.Serializable;

public class Dog implements Serializable {
    private String name;
    private int age;
    private String color;
    transient private Bitmap bitmap;

    public Dog(String name, int age, String color, Bitmap bitmap) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.bitmap = bitmap;
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

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException{
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,out);
        out.defaultWriteObject();
    }
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException{
        bitmap = BitmapFactory.decodeStream(in);
        in.defaultReadObject();
    }

}