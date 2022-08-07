package edu.metrostate.sheltertracker;

//import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Type;
//import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

//import org.json.simple.JSONObject;
//import org.json.simple.JSONArray;
//import org.json.simple.parser.ParseException;
//import org.json.simple.parser.JSONParser;

public class State implements Reader,Writer{
    String filename = "state.json";

    //JsonParser jsonParser = new JSONParser();
    String in;
    JSONObject jsonReader = new JSONObject(in);

    JSONObject jsonObject;

    public State() throws JSONException {
    }

    /** This method checks if a file exists. If it does, it will create it to a JSON object and close the file
     */
    public boolean openFile(String filename) {
        boolean fileOpened = false;

        try (FileReader reader = new FileReader(filename)) {
            //Read JSON file

            //jsonObject = (JSONObject) jsonParser.parse(reader);
            jsonObject = (JSONObject) jsonReader.getJSONObject(filename);

            reader.close();
            fileOpened = true; // this means the code was able to open the JSON file
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
            return fileOpened;
        }
    }

    //    This receives two empty lists passed from main and fills them with items from json file
    public boolean parseFile(List<Shelter> shelterList, List<Animal> animalsOutsideShelters) throws JSONException {
        boolean fileParsed = true;

        JSONArray shelterRoster = (JSONArray) jsonObject.get("shelter_roster");

        //int shelterLength = shelterRoster.toArray().length;
        int shelterLength = shelterRoster.length();
        //Object[] shelterArray = shelterRoster.toArray();

        for (int i = 0; i < shelterLength; i++) {


//              These are used to create the empty shelter objects

            Boolean receiving_animal = (Boolean) ((JSONObject) shelterRoster.get(i)).get("receiving_animal");
            String shelter_name = (String) ((JSONObject) shelterRoster.get(i)).get("shelter_name");
            String shelter_id = (String) ((JSONObject) shelterRoster.get(i)).get("shelter_id");

            Shelter newShelter = new Shelter(shelter_id, null);

            newShelter.setShelterName(shelter_name);

            //JSONObject animalRosterObject =
            JSONArray animalRoster = (JSONArray) ((JSONObject) shelterRoster.get(i)).get("animal_list");

            //int animalLength = animalRoster.toArray().length;
            int animalLength = animalRoster.length();
            //Object[] animalArray = animalRoster.toArray();

            for (int j = 0; j < animalLength; j++) {
//              These are used to create the animal objects

                String animal_type = (String) ((JSONObject) animalRoster.get(j)).get("animal_type");
                String animal_name = (String) ((JSONObject) animalRoster.get(j)).get("animal_name");
                String animal_id = (String) ((JSONObject) animalRoster.get(j)).get("animal_id");
                double weight = (double) ((JSONObject) animalRoster.get(j)).get("weight");
                long receipt_date = (long) ((JSONObject) animalRoster.get(j)).get("receipt_date");
                String weight_unit = (String) ((JSONObject) animalRoster.get(j)).get("weight_unit");

                //if (shelterList.size() == 0) {
                //    shelterList.add(newShelter);
                //}

                if (shelter_id == null) {
                    animalsOutsideShelters.add(new Animal(null, animal_type, animal_name, animal_id, weight, receipt_date, weight_unit));
                }
                else {
                    newShelter.acceptAnimal(new Animal(shelter_id, animal_type, animal_name, animal_id, weight, receipt_date, weight_unit));
                }

//              If the shelter list is not empty, check for identical shelter ids
                /* boolean notIdentical = true;
                for (int k = 0; k < shelterList.size(); k++) {
                    if (shelterList.get(k).toString().equals(newShelter.toString())) {
                        notIdentical = false;
                        shelterList.get(k).add_incoming_animal(new Animal(shelter_id, animal_type, animal_name, animal_id, weight, receipt_date));
                    }
                }
                if (notIdentical) {
                    newShelter.add_incoming_animal(new Animal(shelter_id, animal_type, animal_name, animal_id, weight, receipt_date));
                    shelterList.add(newShelter);
                }
                // If there isn't a shelter_id listed
                if (shelter_id.equals("")) {
                    animalsOutsideShelters.add(new Animal(shelter_id, animal_type, animal_name, animal_id, weight, receipt_date));
                } */
            }

            if (!receiving_animal) {
                newShelter.changeAvailability();
            }

            if (shelter_id != null) {
                shelterList.add(newShelter);
            }

//              Logic checks if the shelter exists
//              If the shelter list is empty, add the first shelter object to the list
        /*  if (shelterList.size() == 0) {
                shelterList.add(newShelter);
            }


//              If the shelter list is not empty, check for identical shelter ids
            boolean notIdentical = true;
            for (int j = 0; j < shelterList.size(); j++) {
                if (shelterList.get(j).toString().equals(newShelter.toString())) {
                    notIdentical = false;
                    shelterList.get(j).add_incoming_animal(new Animal(shelter_id, animal_type, animal_name, animal_id, weight, receipt_date));
                }
            }
            if (notIdentical) {
                newShelter.add_incoming_animal(new Animal(shelter_id, animal_type, animal_name, animal_id, weight, receipt_date));
                shelterList.add(newShelter);
            }
            // If there isn't a shelter_id listed
            if (shelter_id.equals("")) {
                animalsOutsideShelters.add(new Animal(shelter_id, animal_type, animal_name, animal_id, weight, receipt_date));
            }
         */
        }

        return fileParsed;
    }

    //    This gets the two full lists from main and writes them to state.json
    public boolean write(List<Shelter> shelterList, List<Animal> animalsOutsideShelters){
        boolean fileWritten = true;

        try {
//          These variables are used to convert the List of animals into a JSONArray,
//          which can be easily converted into a JSON string
            JSONArray shelterJSONArray = new JSONArray();

            for (Shelter shelter : shelterList) {
                JSONObject newShelter = new JSONObject();

                newShelter.put("shelter_id", shelter.toString());
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

            if (animalsOutsideShelters.size() > 0) {
                JSONObject newShelter = new JSONObject();

                newShelter.put("shelter_id", null);
                newShelter.put("shelter_name", null);
                newShelter.put("receiving_animal", true);

                JSONArray animalJSONArray = new JSONArray();

                for (Animal shelterAnimal : animalsOutsideShelters) {
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

            String JSONFilename = filename;
            FileWriter myWriter = new FileWriter(JSONFilename);

            //myWriter.write(writeToJSON.toJSONString());
            myWriter.write(writeToJSON.toString());

            myWriter.close();
            //System.out.println("Successfully wrote to the file.");
            //System.out.println("Successfully wrote to the file.");


        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return fileWritten;
    }

}
