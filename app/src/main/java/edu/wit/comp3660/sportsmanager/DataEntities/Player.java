package edu.wit.comp3660.sportsmanager.DataEntities;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import edu.wit.comp3660.sportsmanager.R;

public class Player {

    public Bitmap image;
    public String name;
    public int weight;
    public int height;
    public String jerseyNumber;
    public String phoneNumber;
    public Position preferredPosition;
    public String notes;

    private Context context;

    public Player(Context current) {
        this.context = current;
        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.jones);
    }

    public Player() {
        jerseyNumber = "00";
    }

    public String getWeightText() {
        return weight + " lbs";
    }

    public String getHeightText() {
        return height/12 + "' " + height%12 + "\"";
    }

    public int getPreferredPositionIndex() {
        Position[] positions = LoadedData.get().getCurrentTeam().getSport()
                .getPositions().toArray(new Position[0]);
        for(int i = 0; i < positions.length; i++) {
            if (positions[i] == preferredPosition)
                return i+1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return name;
    }
}


