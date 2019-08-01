package edu.wit.comp3660.sportsmanager.DataEntities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.List;

import edu.wit.comp3660.sportsmanager.R;

public class Player {

    private Bitmap image;
    public String name;
    public int weight;
    public int height;
    public String jerseyNumber;
    public String phoneNumber;
    public Position preferredPosition;
    public String notes;
    public List bitmapList;

    private Context context;

    public Player(Context current, String name, String jerseyNumber) {
        this.context = current;
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.jones);
        //bitmapList = Arrays.asList(image);
    }

    public Player() {
        name = "Empty";
        jerseyNumber = "";
    }

    public Bitmap playerImage() {
        return image;
    }

    public void setPlayerImage(Bitmap b) {
        image = b;
        //bitmapList = Arrays.asList(image);
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


