package edu.wit.comp3660.sportsmanager.DataEntities;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {

    private String name;
    private Sport sport;
    private ArrayList<Player> roster;
    private ArrayList<Lineup> lineup;
    private ArrayList<Game> games;

    public Team(String name, Sport sport) {
        this.name = name;
        this.sport = sport;
        this.roster = new ArrayList<>();
        this.lineup = new ArrayList<>();
        this.games = new ArrayList<>();
    }

    public Team() {
        // we need this empty constructor, otherwise Firebase will error out
        this.name = "error";
    }

    public String getName() {
        return name;
    }

    public Sport getSport() {
        return sport;
    }

    public ArrayList<Player> getRoster() {
        return roster;
    }

    public ArrayList<Lineup> getLineup() {
        return lineup;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    @Override
    public String toString() {
        return name + " ("+sport+")";
    }
}
