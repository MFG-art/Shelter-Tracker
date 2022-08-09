package edu.metrostate.sheltertracker;

//import android.util.Log;

import com.google.gson.*;
import org.json.*;
import java.io.*;
import java.util.*;

public class State implements Reader, Writer {

    JsonParser jsonParser = new JsonParser();
    JsonObject jsonObject;

    /** This method checks if the state exists. If it does, it will create it to a JSON object and close the file
     */
    public boolean openFile(File file) {
        boolean fileOpened = false;

        try (FileReader reader = new FileReader(file)) {

            //Read JSON file
            jsonObject = (JsonObject) jsonParser.parseReader(reader);

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

    //    This receives two empty lists passed from main and fills them with items from json file
    public boolean parseFile(List<Shelter> shelterList, List<Animal> animalsOutsideShelters) {
        boolean fileParsed = true;

        JsonArray shelterRoster = (JsonArray) jsonObject.get("shelter_roster");

        int shelterLength = shelterRoster.size();

        for (int i = 0; i < shelterLength; i++) {


//              These are used to create the empty shelter objects
            Boolean receiving_animal;
            String shelter_name;
            String shelter_id;

            receiving_animal = (((JsonObject) shelterRoster.get(i)).get("receiving_animal")).getAsBoolean();
            shelter_name = (((JsonObject) shelterRoster.get(i)).get("shelter_name")).getAsString();
            shelter_id = (((JsonObject) shelterRoster.get(i)).get("shelter_id")).getAsString();

            Shelter newShelter = new Shelter(shelter_id, "null");

            newShelter.setShelterName(shelter_name);

            JsonArray animalRoster = (JsonArray) ((JsonObject) shelterRoster.get(i)).get("animal_list");

            int animalLength = animalRoster.size();

            for (int j = 0; j < animalLength; j++) {
//              These are used to create the animal objects

                String animal_type;
                String animal_name;
                String animal_id;
                double weight;
                long receipt_date;
                String weight_unit ;

                animal_type = (((JsonObject) animalRoster.get(j)).get("animal_type")).getAsString();
                animal_name = (((JsonObject) animalRoster.get(j)).get("animal_name")).getAsString();
                animal_id = (((JsonObject) animalRoster.get(j)).get("animal_id")).getAsString();
                weight = (((JsonObject) animalRoster.get(j)).get("weight")).getAsDouble();
                receipt_date = (((JsonObject) animalRoster.get(j)).get("receipt_date")).getAsLong();
                weight_unit = (((JsonObject) animalRoster.get(j)).get("weight_unit")).getAsString();

                if (!shelter_id.equals("null")) {
                    newShelter.acceptAnimal(new Animal(shelter_id, animal_type, animal_name, animal_id, weight, receipt_date, weight_unit));
                }
            }

            if (!receiving_animal) {
                newShelter.changeAvailability();
            }

            if (!shelter_id.equals("null")) {
                shelterList.add(newShelter);
            }
        }

        return fileParsed;
    }

    //    This gets the two full lists from main and writes them to state.json
    public JSONObject write(List<Shelter> shelterList, List<Animal> animalsOutsideShelters) throws JSONException {

        //These variables are used to convert the List of animals into a JSONArray,
        //which can be easily converted into a JSON string

        JSONArray shelterJSONArray = new JSONArray();

        for (Shelter shelter : shelterList) {
            JSONObject newShelter = new JSONObject();

            newShelter.put("shelter_id", shelter.getShelterId());
            newShelter.put("shelter_name", shelter.getShelterName());
            newShelter.put("receiving_animal", shelter.getReceivingAnimal());

            JSONArray animalJSONArray = new JSONArray();

//              Add every element in the List to the JSONArray
            for (Animal shelterAnimal : shelter.getAnimalList()) {
                JSONObject newAnimal = new JSONObject();
                newAnimal.put("shelter_id", shelterAnimal.getShelterId());
                newAnimal.put("animal_type", shelterAnimal.getAnimalType());
                newAnimal.put("animal_name", shelterAnimal.getAnimalName());
                newAnimal.put("animal_id", shelterAnimal.getAnimalId());
                newAnimal.put("weight", shelterAnimal.getWeight());
                newAnimal.put("receipt_date", shelterAnimal.getReceiptDate());
                newAnimal.put("weight_unit", shelterAnimal.getWeightUnit());

                animalJSONArray.put(newAnimal);
            }

            newShelter.put("animal_list", animalJSONArray);

            shelterJSONArray.put(newShelter);

        }

        JSONObject writeToJSON = new JSONObject();
        writeToJSON.put("shelter_roster", shelterJSONArray);

        String JSONFilename = "state.json";

        return writeToJSON;
    }
}
