package edu.metrostate.sheltertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AcceptingAnimalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepting_animal);

        ListView lv = findViewById(R.id.shelter_list);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textview = view.findViewById(R.id.shelter_id);
                Intent intent = new Intent(AcceptingAnimalActivity.this, MainActivity.class);
                intent.putExtra("Shelter ID",textview.getText());
                startActivity(intent);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "The animal was received",
                        Toast.LENGTH_SHORT);

                toast.show();

                String animalID = getIntent().getStringExtra("Animal ID");
                TextView shelterView = findViewById(R.id.shelter_id);
                String shelterID = (String) shelterView.getText();
                List<Animal> animalsOutsideShelters = ((ShelterTrackerApplication)getApplication()).getAnimalsOutsideShelters();
                List<Shelter> shelterList = ((ShelterTrackerApplication)getApplication()).getShelterList();

                int position = -1;
                int count = 0;

                for (Animal animal :animalsOutsideShelters){
                    if ((animal.getAnimalId()).equals(animalID)) {
                        position = count;
                    }
                    ++count;
                }

                Animal animal = animalsOutsideShelters.remove(position);

                position = -1;
                count = 0;

                for (Shelter shelter: shelterList){
                    if ((shelter.getShelterId()).equals(shelterID)) {
                        position = count;
                    }
                    ++count;
                }

                shelterList.get(position).acceptAnimal(animal);


            }
        });

        lv.setAdapter(new ShelterAdapter(this,
                ((ShelterTrackerApplication)getApplication()).getShelterList()));
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