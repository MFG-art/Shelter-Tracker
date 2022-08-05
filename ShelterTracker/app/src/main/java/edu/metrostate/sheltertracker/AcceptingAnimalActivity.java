package edu.metrostate.sheltertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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