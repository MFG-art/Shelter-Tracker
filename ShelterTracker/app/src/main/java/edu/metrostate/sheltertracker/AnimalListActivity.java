package edu.metrostate.sheltertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class AnimalListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_list);

        String shelterID = getIntent().getStringExtra("Shelter ID");

        ListView lv = findViewById(R.id.animal_list);

        if (shelterID == null){
            lv.setAdapter(new AnimalAdapter(this,
                    ((ShelterTrackerApplication)getApplication()).getAnimalsOutsideShelters()));

        } else {
            lv.setAdapter(new AnimalAdapter(this,
                    ((ShelterTrackerApplication)getApplication()).getAnimalsOutsideShelters()));
        }



    }
}