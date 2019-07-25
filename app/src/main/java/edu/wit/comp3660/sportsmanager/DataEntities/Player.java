package edu.wit.comp3660.sportsmanager.DataEntities;
import android.content.Context;
import android.graphics.*;

import edu.wit.comp3660.sportsmanager.R;

public class Player {

    public Bitmap image;
    public String name;
    public int weight;
    public int height;
    public String jerseyNumber;
    public String phoneNumber;

    private Context context;

    public Player(Context current) {
        this.context = current;
        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.jones);
    }

    public Player() {
        jerseyNumber = "00";
    }

    public String getWeightText() {
        return String.valueOf(weight);
    }

}


