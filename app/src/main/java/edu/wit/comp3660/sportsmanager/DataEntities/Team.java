package edu.wit.comp3660.sportsmanager.DataEntities;

import android.graphics.Bitmap;
import java.util.ArrayList;

public class Team {

    private String name;
    private Sport sport;
    private ArrayList<Player> roster;
    private ArrayList<Lineup> lineup;
    private ArrayList<Game> games;
    private String record = "";
    private int[] recordArr = new int[3];
    private Bitmap logo;

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
            recordArr[0]++;
        } else if (tS == oS) {
            recordArr[2]++;
        } else {
            recordArr[1]++;
        }

        record = recordArr[0] + "W - " + recordArr[1] + "L - " + recordArr[2] + "T";
    }

    public void removeGameIfPlayed(Game currentGame) {
        if (currentGame.isPlayed()) {
            if (currentGame.getTeamScore() > currentGame.getOpponentScore())
                recordArr[0]--;
            else if (currentGame.getTeamScore() == currentGame.getOpponentScore())
                recordArr[2]--;
            else recordArr[1]--;
        }
    }

    public Game getNextGame() {
        for (int i = 0; i < games.size(); i++) {
            if (!games.get(i).isPlayed()) {
                return games.get(i);
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return name + " (" + sport + ")";
    }
}
