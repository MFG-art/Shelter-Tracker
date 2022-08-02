package edu.metrostate.sheltertracker;

import java.util.List;
import java.util.ArrayList;

/*
This class is used to represent the shelter objects
 */

public class Shelter {
    private final String shelterId;
    private final String shelterName;
    private Boolean receivesAnimals;
    private final List<Animal> animalList = new ArrayList<>();

    public Shelter(String shelterId, String shelterName) {
        this.shelterId = shelterId;
        this.shelterName = shelterName;
    }

    public String getShelterId() {
        return shelterId;
    }

    public String getShelterName() {
        return shelterName;
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
