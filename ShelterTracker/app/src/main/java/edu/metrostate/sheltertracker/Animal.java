package edu.metrostate.sheltertracker;

import androidx.annotation.NonNull;

public class Animal {
    public String getAnimalName() {
        return animalName;
    }

    public String getAnimalId() {
        return animalId;
    }

    public double getWeight() {
        return weight;
    }

    public String getAnimalType() {
        return animalType;
    }

    public long getReceiptDate() {
        return receiptDate;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    private String animalName;
    private String animalId;
    private double weight;
    private String animalType;
    private long receiptDate;
    private String weightUnit;

    @NonNull
    public String toString(){
        return this.animalName + " the " + this.animalType;
    }
}
