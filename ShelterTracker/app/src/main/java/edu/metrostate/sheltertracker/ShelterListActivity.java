package edu.metrostate.sheltertracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsetsController;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShelterListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_list);

        ListView lv = findViewById(R.id.shelter_list);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView idTextView = view.findViewById(R.id.shelter_id);
                TextView nameTextView = view.findViewById(R.id.shelter_name);
                Intent intent = new Intent(ShelterListActivity.this, ShelterActivity.class);
                intent.putExtra("Shelter ID",idTextView.getText());
                intent.putExtra("Shelter name", nameTextView.getText());

                startActivity(intent);

            }
        });

        lv.setAdapter(new ShelterAdapter(this,
                ((ShelterTrackerApplication)getApplication()).getShelterList()));

    }

    public void returnToMainScreen(View view){
        Intent intent = new Intent(ShelterListActivity.this, MainActivity.class);
        startActivity(intent);
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