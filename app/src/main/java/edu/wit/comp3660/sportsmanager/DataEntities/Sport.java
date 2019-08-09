package edu.wit.comp3660.sportsmanager.DataEntities;

import androidx.annotation.Keep;

import java.util.ArrayList;

public class Sport {

    private String name;
    private ArrayList<Position> positions;

    public Sport(String sportName) {
        this.name = sportName;
        findPositions(sportName);
    }

    @Keep
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

    private void findPositions(String sport) {
        switch(sport) {
            case "Soccer":
                positions = Position.getSoccerPos();
                break;
            case "Basketball":
                positions = Position.getBasketballPos();
                break;
            case "Baseball":
                positions = Position.getBaseballPos();
                break;
            case "Football":
                positions = Position.getFootballPos();
                break;

        }
    }
}
