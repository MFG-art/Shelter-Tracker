package edu.metrostate.sheltertracker;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

/**
 *This interface is implemented by JSONReader.
 *It exists to make it easier to create new classes that need to write to a file
 */

public interface Writer {

    //public boolean openFile(String filename);
    public JSONObject write(List<Shelter> shelterList, List<Animal> animalsOutsideShelters) throws JSONException;


}
