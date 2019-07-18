package edu.wit.comp3660.sportsmanager.ListAdapters;

import edu.wit.comp3660.sportsmanager.DataEntities.Lineup;

public class LineupObject {

    private String playername;
    private String position;
    private String number;

    public LineupObject(String playername, String position, String number) {
        this.playername = playername;
        this.position = position;
        this.number = number;
    }

    public String getPlayername() {
        return playername;
    }

    public String getPosition() {
        return position;
    }

    public String getNumber() {
        return number;
    }
}