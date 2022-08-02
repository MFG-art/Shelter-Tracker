package edu.metrostate.sheltertracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AnimalAdapter extends ArrayAdapter<Animal> {
    public AnimalAdapter(Context context, List<Animal> animalsOutsideShelters) {
        super(context, R.layout.animal_list_item, animalsOutsideShelters);
    }

    /*
    This is used to populate the animal ListView with data from the list of Animal objects
     */

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.animal_list_item, parent, false);
        }

        TextView id = convertView.findViewById(R.id.animal_id);
        TextView name = convertView.findViewById(R.id.animal_name);

        id.setText(getItem(position).getAnimalId());
        name.setText(getItem(position).getAnimalName());


        return convertView;
    }
}
