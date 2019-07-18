package edu.wit.comp3660.sportsmanager.DataEntities;
import android.graphics.*;

public class Player {

    public Bitmap image;
    public String name;
    public int weight;
    public int height;
    public String number;

    public Player() {
        number = "00";
    }

    public String getHeightText() {
        return height/12 + "' " + height%12 +"\"";
    }

    public String getWeightText() {
        return String.valueOf(weight);
    }


}


