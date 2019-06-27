package com.example.sportsmanager.DataEntities;

import java.util.ArrayList;

public class Team {

    private String name;
    private Sport sport;
    private ArrayList<Player> roster;
    private ArrayList<Lineup> lineup;
    private ArrayList<Game> games;

    public Team(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
