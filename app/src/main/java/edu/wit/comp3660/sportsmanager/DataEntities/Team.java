package edu.wit.comp3660.sportsmanager.DataEntities;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;

public class Team {

    private String name;
    private Sport sport;
    private ArrayList<Player> roster;
    private Lineup lineup;
    private ArrayList<Game> games;
    private RecordDictionary recordDict = new RecordDictionary();
    private Bitmap logo;

    public Team(String name, Sport sport) {
        this.name = name;
        this.sport = sport;
        this.roster = new ArrayList<>();
        this.lineup = new Lineup(sport);
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

    public Lineup getLineup() {
        if (lineup == null || lineup.isEmpty())
            lineup = new Lineup(sport);
        return lineup;
    }

    public String getRecordText() {
        return recordDict.get("W") + "W - "
                + recordDict.get("L") + "L - "
                + recordDict.get("T") + "T";
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public Bitmap getLogo() {
        return logo;
    }

    public void setLogo(Bitmap logo) {
        this.logo = logo;
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public void editGame(String teamScore, String opponentScore) {
        int tS = Integer.parseInt(teamScore);
        int oS = Integer.parseInt(opponentScore);
        Game currentGame = games.get(LoadedData.get().getCurrentGameIndex());
        removeGameIfPlayed(currentGame);
        currentGame.setScore(tS, oS);
        if (tS > oS) {
            recordDict.increment("W");
        } else if (tS == oS) {
            recordDict.increment("T");
        } else {
            recordDict.increment("L");
        }
    }

    public void removeGameIfPlayed(Game currentGame) {
        if (currentGame.isPlayed()) {
            if (currentGame.getTeamScore() > currentGame.getOpponentScore())
                recordDict.decrement("W");
            else if (currentGame.getTeamScore() == currentGame.getOpponentScore())
                recordDict.decrement("T");
            else recordDict.decrement("L");
        }
    }

    public Player findPlayer(String playerId) {
        for (Player e: roster)
            if (e.name.equals(playerId))
                return e;
        return null;
    }

    public Game getNextGame() {
        for (int i = 0; i < games.size(); i++) {
            if (!games.get(i).isPlayed()) {
                return games.get(i);
            }
        }

        return null;
    }

    public RecordDictionary getRecordDict() {
        return recordDict;
    }


    @Override
    public String toString() {
        return name + " (" + sport + ")";
    }
}

class RecordDictionary extends HashMap<String, Integer> {
    RecordDictionary() {
        super();
        for (String key: new String[]{"W", "L", "T"})
            put(key, 0);
    }
    void increment(String key) {
        Integer val = super.get(key);
        if (val == null) return;
        super.put(key, val+1);
    }
    void decrement(String key) {
        Integer val = super.get(key);
        if (val == null) return;
        super.put(key, val-1);
    }
}