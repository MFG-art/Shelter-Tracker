package edu.metrostate.sheltertracker;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONArray;

//import java.io.File;

public class JSONWriter extends AppCompatActivity {

    public boolean write(List<Shelter> shelterList, List<Animal> animalsOutsideShelters) {
        return true;
    }

    public JSONObject writeShelter(List<Shelter> shelterList, List<Animal> animalsOutsideShelters, String shelterID) throws JSONException {
        boolean fileWritten = false;
        for (Shelter shelter : shelterList) {
            if (shelter.getShelterId().equals(shelterID)) {
                //try {

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

                    //File externalDir = getExternalFilesDir(null);

                    //File outputFile = new File(externalDir, "myfile.txt");

                    //try {
                    //    Files.createFile(outputFile.toPath());
                    //    Files.write(outputFile.toPath(), "My data".getBytes());

                    //} catch (IOException ex) {
                    //    Log.e("FileCreation", "Error creating file", ex);
                    //}

                    /*Writer output = null;
                    File file = new File("storage/sdcard/Android/data/edu.metrostate.sheltertracker/files/" + JSONFilename);
                    output = new BufferedWriter(new FileWriter(file));
                    Log.d("successssssssssssdafdvcvcvbsss", "test fail");
                    output.write(writeToJSON.toString());
                    output.close();*/

                    Log.d("successssssssssssdafdvcvcvbsss", writeToJSON.toString());

                    return writeToJSON;

                    //FileWriter myWriter = new FileWriter(JSONFilename);

                    //myWriter.write(writeToJSON.toJSONString());
                    //myWriter.write(writeToJSON.toString());
                    //myWriter.write(writeToJSON.toJSONArray());

                    //myWriter.close();
                    //fileWritten = true;
                //} catch (Exception e) {
                //    Log.d("successssssssssssdafdvcvcvbsss", "didnt create file");
                //    System.out.println("An error occurred.");
                //    e.printStackTrace();
                //}

            }

        }
        return null;
    }
}


