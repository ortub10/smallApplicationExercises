package com.example.module5lesson2drill1;

public class Car {
    private double km;
    private double fuel;
    private double fuelPer100Km;

    public Car(double km, double fuel, double fuelPer100Km) {
        this.km = km;
        this.fuel = fuel;
        this.fuelPer100Km = fuelPer100Km;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public double getFuelPer100Km() {
        return fuelPer100Km;
    }

    public void setFuelPer100Km(double fuelPer100Km) {
        this.fuelPer100Km = fuelPer100Km;
    }

    public void drive(double km){
        double fuelNeeded = (fuelPer100Km/100)*km;
        if (fuelNeeded<=fuel){
            fuel-=fuelNeeded;
            this.km+=km;
        }

    }
}
