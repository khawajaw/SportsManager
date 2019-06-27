package com.example.sportsmanager.DataEntities;

import java.util.ArrayList;

public class LoadedData {

    private static ArrayList<Team> teams = new ArrayList<>();
    public static int currentTeamIndex;

    public static void createTeam(String name) {
        teams.add(new Team(name));
    }

    public static Team getCurrentTeam() {
        return teams.get(currentTeamIndex);
    }

    public static ArrayList<Team> getTeams() {
        return teams;
    }


}
