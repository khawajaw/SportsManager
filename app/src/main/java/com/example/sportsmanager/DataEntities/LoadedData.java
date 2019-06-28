package com.example.sportsmanager.DataEntities;

import java.util.ArrayList;

public class LoadedData {

    private static ArrayList<Team> teams = new ArrayList<>();
    public static int currentTeamIndex;
    public static final Sport[] SPORTS = {
            new Sport("Football"),
            new Sport("Soccer"),
            new Sport("Baseball"),
            new Sport("Basketball"),
    };

    public static void createTeam(String name, Sport sport) {
        teams.add(new Team(name, sport));
    }

    public static Team getCurrentTeam() {
        return teams.get(currentTeamIndex);
    }

    public static ArrayList<Team> getTeams() {
        return teams;
    }


}
