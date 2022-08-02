package edu.metrostate.sheltertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsetsController;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ShelterListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shelter_list);

        ListView lv = findViewById(R.id.shelter_list);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tview = view.findViewById(R.id.shelter_id);
                Intent intent = new Intent(ShelterListActivity.this, AnimalListActivity.class);
                intent.putExtra("Shelter ID",tview.getText());

                startActivity(intent);

            }
        });

        lv.setAdapter(new ShelterAdapter(this,
                ((ShelterTrackerApplication)getApplication()).getShelterList()));

    }
}