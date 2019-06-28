package com.example.sportsmanager.DataEntities;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Sport {

    private String name;
    private ArrayList<Position> positions;
    private Bitmap logo;

    public Sport(String sportName) {
        this.name = sportName;
    }

    @Override
    public String toString() {
        return name;
    }
}
