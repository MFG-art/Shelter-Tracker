package edu.metrostate.sheltertracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShelterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter);

        String shelterID = getIntent().getStringExtra("Shelter ID");

        ListView lv = findViewById(R.id.animal_list);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                This AlertDialog is used to ask the user if they want to remove the animal from the shelter
                AlertDialog.Builder builder = new AlertDialog.Builder(ShelterActivity.this);
                builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);

                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        If the user clicks ok, remove the animal from the shelter
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "The animal was removed from the shelter",
                                Toast.LENGTH_SHORT);

                        toast.show();
                    }
                });

                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        If the user does not want the animal removed from shelter
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        lv.setAdapter(new AnimalAdapter(this,
                    ((ShelterTrackerApplication)getApplication()).getAnimalsOutsideShelters()));

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