package edu.metrostate.sheltertracker;

import java.util.List;
import java.util.ArrayList;

/*
This class is used to represent the shelter objects
 */

public class Shelter {
    private String shelterId;
    private String shelterName;
    private Boolean receivesAnimals;
    private List<Animal> animalList = new ArrayList<Animal>();

    public Shelter(String shelterId, String shelterName) {
        this.shelterId = shelterId;
        this.shelterName = shelterName;
        this.receivesAnimals = true;
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

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public boolean getReceivingAnimal() {
        return receivesAnimals;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void removeAnimal(Animal animal, List<Animal> animalsOutsideShelters) {
        animalList.remove(animal);
        animalsOutsideShelters.add(animal);
    }
}
