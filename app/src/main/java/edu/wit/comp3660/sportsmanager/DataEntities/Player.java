package edu.wit.comp3660.sportsmanager.DataEntities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.nio.ByteBuffer;

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
    public String bitmapBytes;

    private Context context;

    public Player(Context current, String name, String jerseyNumber) {
        this.context = current;
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        setPlayerImage(BitmapFactory.decodeResource(context.getResources(), R.drawable.jones));
    }

    public Player() {
        name = "Empty";
        jerseyNumber = "";
    }

    public Bitmap playerImage() {
        loadBitmapFromBytes();
        return image;
    }

    public void setPlayerImage(Bitmap b) {
        image = b;
        bitmapBytes = getBitmapBytes();
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

    public String getBitmapBytes() {
        int size = image.getRowBytes() * image.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        image.copyPixelsToBuffer(byteBuffer);
        bitmapBytes = Base64.encodeToString(byteBuffer.array(), Base64.DEFAULT);
        return "";
        //  store & retrieve this string to firebase
    }

    private boolean loadBitmapFromBytes() {
        byte[] byteArray = Base64.decode(bitmapBytes, Base64.DEFAULT);
        if (bitmapBytes != null && byteArray.length > 0) {
            ByteBuffer buffer = ByteBuffer.wrap(byteArray);
            image.copyPixelsFromBuffer(buffer);
            return true;
        }
        else return false;
    }

    @Override
    public String toString() {
        return name;
    }
}


