package edu.wit.comp3660.sportsmanager.DataEntities;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {

    private String name;
    private Sport sport;
    private ArrayList<Player> roster;
    private ArrayList<Lineup> lineup;
    private ArrayList<Game> games;
    private String record = "";
    private int[] recordArr = new int[3];

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

    public String getRecord() {
        return record;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public void editGame(String teamScore, String opponentScore) {
        int tS = Integer.parseInt(teamScore);
        int oS = Integer.parseInt(opponentScore);
        games.get(LoadedData.get().getCurrentGameIndex()).setScore(tS, oS);
        if(tS > oS) {
            recordArr[0]++;
        }
        else if(tS == oS) {
            recordArr[2]++;
        }
        else {
            recordArr[1]++;
        }

        record = recordArr[0] + "W - " + recordArr[1] + "L - " + recordArr[2] + "T";
    }

    public Game getNextGame() {
        for(int i = 0; i < games.size(); i++) {
            if(!games.get(i).isPlayed()) {
                return games.get(i);
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return name + " ("+sport+")";
    }
}
