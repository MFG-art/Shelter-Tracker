package edu.metrostate.sheltertracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    String file;

    //JSONParser jsonParser = new JSONParser();
    String in;
    JSONObject jsonReader = new JSONObject(in);

    JSONObject jsonObject;

    public JSONReader() throws JSONException {
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
    public boolean parseFile(List<Shelter> shelterList, List<Animal> animalsOutsideShelters) throws JSONException {
        boolean fileParsed = false;

        JSONArray shelterRoster = (JSONArray) jsonObject.get("shelter_roster");

        //int length = shelterRoster.toArray().length;
        int length = shelterRoster.length();
        //Object[] array = shelterRoster.toArray();
        //Object[] array =

        for (int i = 0; i < length; i++) {


//              These are used to create the empty shelter objects

            String shelter_id = (String) ((JSONObject) shelterRoster.get(i)).get("shelter_id");
            Shelter newShelter = new Shelter(shelter_id, null);

//              These are used to create the animal objects

            String animal_type = (String) ((JSONObject) shelterRoster.get(i)).get("animal_type");
            String animal_name = (String) ((JSONObject) shelterRoster.get(i)).get("animal_name");
            String animal_id = (String) ((JSONObject) shelterRoster.get(i)).get("animal_id");
            double weight = (double) (long) ((JSONObject) shelterRoster.get(i)).get("weight");
            long receipt_date = (long) ((JSONObject) shelterRoster.get(i)).get("receipt_date");
            String weight_unit = null;

            // If there isn't a shelter_id listed
            if (shelter_id == null) {
                animalsOutsideShelters.add(new Animal(null, animal_type, animal_name, animal_id, weight, receipt_date, null));
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
                    if (shelterList.get(j).toString().equals(newShelter.toString())) {
                        shelterNotIdentical = false;

                        if (shelterList.get(j).getAnimalList().size() == 0) {
                            shelterList.get(j).acceptAnimal(new Animal(shelter_id, animal_type, animal_name, animal_id, weight, receipt_date, null));
                        }

                        boolean animalNotIdentical = true;

                        for (int k = 0; k < shelterList.get(j).getAnimalList().size(); k++) {
                            if (shelterList.get(j).getAnimalList().get(k).getAnimalId().equals(animal_id)) {
                                animalNotIdentical = false;
                            }
                        }

                        if (animalNotIdentical) {
                            shelterList.get(j).acceptAnimal(new Animal(shelter_id, animal_type, animal_name, animal_id, weight, receipt_date, null));
                        }
                    }
                }
                if (shelterNotIdentical) {
                    newShelter.acceptAnimal(new Animal(shelter_id, animal_type, animal_name, animal_id, weight, receipt_date, null));
                    shelterList.add(newShelter);
                }
            }
        }

        return fileParsed;
    }

}
