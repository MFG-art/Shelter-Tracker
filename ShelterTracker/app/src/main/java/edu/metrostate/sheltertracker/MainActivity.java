package edu.metrostate.sheltertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*File externalDir = getExternalFilesDir(null);
        File stateFile = new File(externalDir, "state.json");

        State state = new State();
        if (state.openFile(stateFile)) {
            state.parseFile(((ShelterTrackerApplication)getApplication()).getShelterList(), ((ShelterTrackerApplication)getApplication()).getAnimalsOutsideShelters());
        }*/
    }

//    When the app is closed, this will run and call the write state method
    @Override
    protected void onDestroy() {
        /*State state = new State();
        JSONObject writeToJSON = state.write(((ShelterTrackerApplication)getApplication()).getShelterList(), ((ShelterTrackerApplication)getApplication()).getAnimalsOutsideShelters());

        File externalDir = getExternalFilesDir(null);
        File stateFile = new File(externalDir, "state.json");

        try {
            FileWriter myWriter = new FileWriter(stateFile);
            myWriter.write(writeToJSON.toString());
            myWriter.close();
        } catch (IOException ex) {
            Log.d("successssssssssssdafdvcvcvbsss", "didnt create state file");
            Log.e("FileCreation", "Error creating file", ex);
        }*/

        Toast toast = Toast.makeText(getApplicationContext(),
                "Successfully wrote state",
                Toast.LENGTH_SHORT);
        toast.show();

        super.onDestroy();
    }

    //    This is called by the SHOW SHELTERS button
    public void showShelterList(View view) {
        Intent intent = new Intent(this, ShelterListActivity.class);
        startActivity(intent);
    }

    // This can be called by the SHOW ANIMALS OUTSIDE SHELTERS button
    public void showAnimalsOutsideShelters(View view){
        Intent intent = new Intent(this, AnimalListActivity.class);
        startActivity(intent);
    }

    public void readFile(View view) throws JSONException {
        EditText filename = findViewById(R.id.filename);
        String userInput = String.valueOf(filename.getText());

//        code from reading json or xml class will receive a filename string
        String[] splitFilename = userInput.split("\\.");
        String fileType = splitFilename[1];

        if (fileType.equals("json")) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Its json",
                    Toast.LENGTH_SHORT);
            toast.show();

            File externalDir = getExternalFilesDir(null);
            File inputFile = new File(externalDir, userInput);

            JSONReader jsonReader = new JSONReader();
            if (jsonReader.openFile(inputFile)) {
                jsonReader.parseFile(((ShelterTrackerApplication) getApplication()).getShelterList(), ((ShelterTrackerApplication) getApplication()).getAnimalsOutsideShelters());
            }

        } else if (fileType.equals("xml")){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Its xml",
                    Toast.LENGTH_SHORT);
            toast.show();

            File externalDir = getExternalFilesDir(null);
            File file = new File(externalDir, userInput);

            XMLReaderDOM xmlReaderDOM = new XMLReaderDOM();
            xmlReaderDOM.fileReader(file, ((ShelterTrackerApplication)getApplication()).getShelterList(), ((ShelterTrackerApplication)getApplication()).getAnimalsOutsideShelters());

            Toast toast2 = Toast.makeText(getApplicationContext(),
                    ((ShelterTrackerApplication)getApplication()).getShelterList().get(0).getShelterName(),
                    Toast.LENGTH_SHORT);
            toast2.show();
        }

        Log.d("successssssssssssdafdvcvcvbsssqqqqqqqqqqqqqq", ((ShelterTrackerApplication)getApplication()).getShelterList().get(0).getAnimalList().get(0).getAnimalName());

//        This code will run if the file is successfully read
        Toast toast = Toast.makeText(getApplicationContext(),
                "Successfully read file",
                Toast.LENGTH_SHORT);
        toast.show();

        Log.d("successssssssssssdafdvcvcvbsss", "sus");

        State state = new State();
        JSONObject writeToJSON = state.write(((ShelterTrackerApplication)getApplication()).getShelterList(), ((ShelterTrackerApplication)getApplication()).getAnimalsOutsideShelters());

        Log.d("successssssssssssdafdvcvcvbsss", "sus");

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

//        clearing the filename input box

        filename.setText("");

    }


    public void testExportAnimals(View view) throws JSONException {
        JSONWriter jsonWriter = new JSONWriter();
        JSONObject writeToJSON = jsonWriter.writeShelter(((ShelterTrackerApplication)getApplication()).getShelterList(), ((ShelterTrackerApplication)getApplication()).getAnimalsOutsideShelters(), "12513");

        File externalDir = getExternalFilesDir(null);
        File outputFile = new File(externalDir, "Shelter_12513.json");

        try {
            FileWriter myWriter = new FileWriter(outputFile);
            myWriter.write(writeToJSON.toString());
            myWriter.close();
        } catch (IOException ex) {
            Log.e("FileCreation", "Error creating file", ex);
        }
    }
}