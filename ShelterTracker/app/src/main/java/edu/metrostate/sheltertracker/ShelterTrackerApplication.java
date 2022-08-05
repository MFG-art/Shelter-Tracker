package edu.metrostate.sheltertracker;

import android.app.Application;
import android.content.res.Configuration;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.File;
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
            shelterList.add(new Shelter(Integer.toString(i), "Shelter Number " + i));
            animalsOutsideShelters.add(new Animal("Animal Number "+ i, Integer.toString(i), 0,"",0,""));
        }
        writeFile();
    }

    public List<Shelter> getShelterList() {
        return shelterList;
    }

    public List<Animal> getAnimalsOutsideShelters() {
        return animalsOutsideShelters;
    }

    public void writeFile() {

        // this will put files in the /sdcard/Android/data/edu.metrostate.sheltertracker/files directory
        File externalDir = getExternalFilesDir(null);

        File outputFile = new File(externalDir, "myfile.txt");

        try {
            Files.createFile(outputFile.toPath());
            Files.write(outputFile.toPath(), "My data".getBytes());

        } catch (IOException ex) {
            Log.e("FileCreation", "Error creating file", ex);
        }

    }



}
