package edu.metrostate.sheltertracker;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ShelterTrackerApplication extends Application {

    private final List<Shelter> shelterList = new ArrayList<>();
    private final List<Animal> animalsOutsideShelters = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        for(int i = 0; i < 20; i++) {
            //shelterList.add(new Shelter(Integer.toString(i), "Shelter Number " + i));

            //animalsOutsideShelters.add(new Animal("Animal Number "+ i, Integer.toString(i), 0,"",0,""));

            //animalsOutsideShelters.add(new Animal("Shelter ID","Animal Number "+ i, Integer.toString(i), "",0,0,""));
        }

        File externalDir = getExternalFilesDir(null);
        File stateFile = new File(externalDir, "state.json");

        State state = new State();
        if (state.openFile(stateFile)) {
            state.parseFile(getShelterList(), getAnimalsOutsideShelters());
        }
    }

    /*@Override
    protected void onDestroy() {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Successfully wrote state",
                Toast.LENGTH_SHORT);
        toast.show();

        State state = new State();
        JSONObject writeToJSON = state.write(getShelterList(), getAnimalsOutsideShelters());

        File externalDir = getExternalFilesDir(null);
        File stateFile = new File(externalDir, "state.json");

        try {
            FileWriter myWriter = new FileWriter(stateFile);
            myWriter.write(writeToJSON.toString());
            myWriter.close();
        } catch (IOException ex) {
            Log.d("successssssssssssdafdvcvcvbsss", "didnt create state file");
            Log.e("FileCreation", "Error creating file", ex);
        }

        super.onDestroy();
    }*/

    public List<Shelter> getShelterList() {
        return shelterList;
    }

    public List<Animal> getAnimalsOutsideShelters() {
        return animalsOutsideShelters;
    }

    public File readFile(String filename) {
        //File externalDir = getExternalFilesDir(null);
        //File inputFile = new File(externalDir, filename);

        //Files.f

        //return inputFile;
        return null;
    }

//    public void writeFile() {
//
//        // this will put files in the /sdcard/Android/data/edu.metrostate.sheltertracker/files directory
//        File externalDir = getExternalFilesDir(null);
//
//        File outputFile = new File(externalDir, "myfile.txt");
//
//        try {
//            Files.createFile(outputFile.toPath());
//            Files.write(outputFile.toPath(), "My data".getBytes());
//
//        } catch (IOException ex) {
//            Log.e("FileCreation", "Error creating file", ex);
//        }
//
//    }



}
