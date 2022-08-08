package edu.metrostate.sheltertracker;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.*;


//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

import org.json.JSONException;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//import org.json.simple.JSONObject;
//import org.json.simple.JSONArray;
//import org.json.simple.parser.ParseException;
//import org.json.simple.parser.JSONParser;


public class JSONReader implements Reader{
    //String file;

    JsonParser jsonParser = new JsonParser();
    //String in;
    //JSONObject jsonReader = new JSONObject(in);

    //JsonElement jsonElement;

    JsonObject jsonObject;

    //public JSONReader() throws JSONException {
    //}

    /** This method checks if a file exists. If it does, it will create it to a JSON object and close the file
     */
    public boolean openFile(File file) {
        boolean fileOpened = false;

        /*InputStream is = getActivity().getAssets().open(file);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        String myJson = new String(buffer, "UTF-8");

        return fileOpened;*/

        //String fileName = file.getName();

        //try (FileReader reader = new FileReader(filename)) {
        try (FileReader reader = new FileReader(file)) {
            //Read JSON file

            Log.d("successssssssfasdgfbhcvcssssss", "gadgasdgagfcbngng");

            jsonObject = (JsonObject) jsonParser.parseReader(reader);
            //jsonElement = jsonParser.parse(reader);
            //jsonObject = (JsonObject) jsonElement;
            //jsonObject = (JSONObject) jsonReader.getJSONObject(fileName);

            reader.close();
            fileOpened = true; // this means the code was able to open the JSON file
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return fileOpened;
        }
    }

    /**
     *This method iterates through the json Object and saves all the data to Animal and Shelter objects
     */
    public boolean parseFile(List<Shelter> shelterList, List<Animal> animalsOutsideShelters) {
        boolean fileParsed = false;

        JsonArray shelterRoster = (JsonArray) jsonObject.get("shelter_roster");

        //int length = shelterRoster.toArray().length;
        int length = shelterRoster.size();
        //Object[] array = shelterRoster.toArray();
        //Object[] array =

        for (int i = 0; i < length; i++) {


//              These are used to create the empty shelter objects

            String shelter_id = (((JsonObject) shelterRoster.get(i)).get("shelter_id")).getAsString();
            Shelter newShelter = new Shelter(shelter_id, "null");

//              These are used to create the animal objects

            String animal_type = (((JsonObject) shelterRoster.get(i)).get("animal_type")).getAsString();
            String animal_name = (((JsonObject) shelterRoster.get(i)).get("animal_name")).getAsString();
            String animal_id = (((JsonObject) shelterRoster.get(i)).get("animal_id")).getAsString();
            double weight = (((JsonObject) shelterRoster.get(i)).get("weight")).getAsDouble();
            long receipt_date = (((JsonObject) shelterRoster.get(i)).get("receipt_date")).getAsLong();
            String weight_unit = "null";

            // If there isn't a shelter_id listed
            if (shelter_id == "null") {
                animalsOutsideShelters.add(new Animal("null", animal_type, animal_name, animal_id, weight, receipt_date, "null"));
            }
            else {
//              Logic checks if the shelter exists
//              If the shelter list is empty, add the first shelter object to the list
                if (shelterList.size() == 0) {
                    shelterList.add(newShelter);
                }

    //              If the shelter list is not empty, check for identical shelter ids
                boolean shelterNotIdentical = true;
                for (int j = 0; j < shelterList.size(); j++) {
                    if (shelterList.get(j).getShelterId().equals(newShelter.getShelterId())) {
                        shelterNotIdentical = false;

                        if (shelterList.get(j).getAnimalList().size() == 0) {
                            shelterList.get(j).acceptAnimal(new Animal(shelter_id, animal_type, animal_name, animal_id, weight, receipt_date, "null"));
                        }

                        boolean animalNotIdentical = true;

                        for (int k = 0; k < shelterList.get(j).getAnimalList().size(); k++) {
                            if (shelterList.get(j).getAnimalList().get(k).getAnimalId().equals(animal_id)) {
                                animalNotIdentical = false;
                            }
                        }

                        if (animalNotIdentical) {
                            shelterList.get(j).acceptAnimal(new Animal(shelter_id, animal_type, animal_name, animal_id, weight, receipt_date, "null"));
                        }
                    }
                }
                if (shelterNotIdentical) {
                    newShelter.acceptAnimal(new Animal(shelter_id, animal_type, animal_name, animal_id, weight, receipt_date, "null"));
                    shelterList.add(newShelter);
                }
            }
        }

        return fileParsed;
    }

}
