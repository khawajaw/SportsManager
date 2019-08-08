package edu.wit.comp3660.sportsmanager.DataEntities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Player {

    private Bitmap image;
    public boolean avatarIsSet;
    public String name;
    public int weight;
    public int height;
    public String jerseyNumber;
    public String phoneNumber;
    public Position preferredPosition;
    public String notes;
    public String ID;

    public Player(String name, String jerseyNumber) {
        this.name = name;
        this.jerseyNumber = jerseyNumber;
    }

    public Player() {
        name = "Empty";
        jerseyNumber = "";
    }

    public Player(boolean inRoster) {
        //other fields will be filled in
        if (inRoster) {
            ID = "Player_" + LoadedData.get().user_ID_COUNTER++;
        }
    }

    public Bitmap playerImage() {
        return image;
    }

    public void setPlayerImage(Bitmap b) {
        image = b;
        avatarIsSet = true;
        LoadedData.get().uploadImageToFirestore("avatars/"+ID, image);
    }

    //load from Firebase
    void setPlayerImage(byte[] bytes) {
        image = BitmapFactory.decodeByteArray(bytes, 0,bytes.length);
    }

    public String getWeightText() {
        return weight + " lbs";
    }

    public String getHeightText() {
        return height / 12 + "' " + height % 12 + "\"";
    }

    public int getPreferredPositionIndex() {
        Position[] positions = LoadedData.get().getCurrentTeam().getSport()
                .getPositions().toArray(new Position[0]);
        for (int i = 0; i < positions.length; i++) {
            if (positions[i] == preferredPosition)
                return i + 1;
        }
        return 0;
    }


    @Override
    public String toString() {
        return name;
    }
}


