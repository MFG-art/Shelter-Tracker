package edu.metrostate.sheltertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


}