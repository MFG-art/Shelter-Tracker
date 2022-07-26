package edu.metrostate.sheltertracker;

import java.util.List;
import java.util.ArrayList;

public class Shelter {
    private String shelterId;
    private String shelterName;
    private Boolean receivesAnimals;
    private List<Animal> animalList = new ArrayList<>();

    public Shelter(String shelterId, String shelterName) {
        this.shelterId = shelterId;
        this.shelterName = shelterName;
    }


    public String getShelterId() {
        return shelterId;
    }

    public void setShelterId(String shelterId) {
        this.shelterId = shelterId;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

//    This method will change the availability of a shelter.
//    If a shelter is open to receiving animals, it will become unavailable and vice versa
    public void changeAvailability(){
        receivesAnimals = !receivesAnimals;
    }

//    Adds incoming animal to list
    public void acceptAnimal(Animal animal){
        animalList.add(animal);
    }
}
