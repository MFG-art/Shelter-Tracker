package edu.metrostate.sheltertracker;

import java.io.*;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONArray;

public class JSONWriter implements Writer {
    public boolean openFile(String filename) {
        return true;
    }

    public boolean write(List<Shelter> shelterList, List<Animal> animalsOutsideShelters) {
        return true;
    }

    public boolean writeShelter(List<Shelter> shelterList, List<Animal> animalsOutsideShelters, String shelterID) {
        boolean fileWritten = false;
        for (Shelter shelter : shelterList) {
            if (shelter.getShelterId().equals(shelterID)) {
                try {

                    JSONArray shelterJSONArray = new JSONArray();
                    List<Animal> shelterAnimals = shelter.getAnimalList();

//                  Add every element in the List to the JSONArray
                    for (Animal shelterAnimal : shelterAnimals) {
                        String shelterId = shelterAnimal.getShelterId();
                        JSONObject newAnimal = new JSONObject();
                        newAnimal.put("shelter_id", shelterId);
                        newAnimal.put("animal_type", shelterAnimal.getAnimalType());
                        newAnimal.put("animal_name", shelterAnimal.getAnimalName());
                        newAnimal.put("animal_id", shelterAnimal.getAnimalId());
                        newAnimal.put("weight", shelterAnimal.getWeight());
                        newAnimal.put("receipt_date", shelterAnimal.getReceiptDate());


                        //shelterJSONArray.add(newAnimal);
                        shelterJSONArray.put(newAnimal);
                        //newAnimal.put(null, shelterJSONArray);
                    }
                    JSONObject writeToJSON = new JSONObject();
                    writeToJSON.put("shelter_roster", shelterJSONArray);

                    String JSONFilename = "Shelter_" + shelterID + ".json";
                    FileWriter myWriter = new FileWriter(JSONFilename);

                    //myWriter.write(writeToJSON.toJSONString());
                    myWriter.write(writeToJSON.toString());

                    myWriter.close();
                    fileWritten = true;
                } catch (Exception e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

            }

        }
        return fileWritten;
    }
}


