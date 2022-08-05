package edu.metrostate.sheltertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AnimalListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_list);

        String shelterID = getIntent().getStringExtra("Shelter ID");

        ListView lv = findViewById(R.id.animal_list);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textview = view.findViewById(R.id.animal_id);
                Intent intent = new Intent(AnimalListActivity.this, AcceptingAnimalActivity.class);
                intent.putExtra("Animal ID",textview.getText());
                startActivity(intent);

            }
        });

        if (shelterID == null){
            lv.setAdapter(new AnimalAdapter(this,
                    ((ShelterTrackerApplication)getApplication()).getAnimalsOutsideShelters()));

        } else {
            lv.setAdapter(new AnimalAdapter(this,
                    ((ShelterTrackerApplication)getApplication()).getAnimalsOutsideShelters()));
        }



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
}