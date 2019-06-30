package edu.wit.comp3660.sportsmanager.DataEntities;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Sport {

    private String name;
    private ArrayList<Position> positions;
    private Bitmap logo;

    public Sport(String sportName) {
        this.name = sportName;
    }

    public Sport() {
        // we need this empty constructor, otherwise Firebase will error out
        this.name = "error";
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public Bitmap getLogo() {
        return logo;
    }
}
