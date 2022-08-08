package edu.metrostate.sheltertracker;

import org.json.JSONException;

import java.io.*;
import java.util.*;

/**
 *This interface is implemented by JSONReader and XML reader
 */

public interface Reader {

    public boolean openFile(File file);
    public boolean parseFile(List<Shelter> shelterList, List<Animal> animalsOutsideShelters) throws JSONException;

}
