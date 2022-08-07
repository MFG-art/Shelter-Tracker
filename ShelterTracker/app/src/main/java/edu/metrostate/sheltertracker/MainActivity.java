package edu.metrostate.sheltertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    When the app is closed, this will run and call the write state method
    @Override
    protected void onDestroy() {
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
            JSONReader jsonReader = new JSONReader();
            jsonReader.openFile(userInput);
            jsonReader.parseFile(((ShelterTrackerApplication)getApplication()).getShelterList(), ((ShelterTrackerApplication)getApplication()).getAnimalsOutsideShelters());
        } else if (fileType.equals("xml")){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Its xml",
                    Toast.LENGTH_SHORT);
            toast.show();
            XMLReaderDOM xmlReaderDOM = new XMLReaderDOM();
            xmlReaderDOM.fileReader(userInput, ((ShelterTrackerApplication)getApplication()).getShelterList(), ((ShelterTrackerApplication)getApplication()).getAnimalsOutsideShelters());
        }

//        This code will run if the file is successfully read
        Toast toast = Toast.makeText(getApplicationContext(),
                "Successfully read file",
                Toast.LENGTH_SHORT);
        toast.show();

//        clearing the filename input box

        filename.setText("");

    }


}