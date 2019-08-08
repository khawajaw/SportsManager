package edu.wit.comp3660.sportsmanager.DataEntities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class Team {

    private String name;
    private Sport sport;
    private ArrayList<Player> roster;
    private Lineup lineup;
    private ArrayList<Game> games;
    private RecordDictionary recordDict;
    private Bitmap logo;
    public boolean logoIsSet;
    final String ID;

    public Team(String name, Sport sport) {
        this.name = name;
        this.sport = sport;
        this.roster = new ArrayList<>();
        this.lineup = new Lineup(sport);
        this.recordDict = new RecordDictionary(false);
        this.games = new ArrayList<>();
        this.ID = "Team_" + LoadedData.get().user_ID_COUNTER++;
    }

    public Team() {
        // we need this empty constructor, otherwise Firebase will error out
        this.name = "error";
        this.ID = "Team_ERROR";
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
        if (lineup == null)
            lineup = new Lineup(sport);
        return lineup;
    }

    public String generateRecordText() {
        return recordDict.wins + "W - "
                + recordDict.losses + "L - "
                + recordDict.ties + "T";
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public Bitmap logo() {
        return logo;
    }

    public void changeLogo(Bitmap logo) {
        this.logo = logo;
        this.logoIsSet = true;
        LoadedData.get().uploadImageToFirestore("logos/"+ID, logo);
    }

    public void loadLogo(byte[] bytes) {
        this.logo = BitmapFactory.decodeByteArray(bytes, 0,bytes.length);
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
        recordDict.updateRecordFromNewScore(tS, oS);
    }

    public void removeGameIfPlayed(Game currentGame) {
        if (currentGame.isPlayed()) {
            recordDict.updateRecordFromRemovedScore(currentGame.getTeamScore(), currentGame.getOpponentScore());
        }
        LoadedData.get().syncAllDataToFirebase();
    }

    public Player findPlayer(String playerId) {
        for (Player e: roster)
            if (e.ID.equals(playerId))
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

class RecordDictionary {

    int wins;
    int losses;
    int ties;

    public RecordDictionary() {
        //for Firebase
    }
    RecordDictionary(boolean fromFirebase) {
        super();
        //use this so Firebase doesn't reset everything to 0 (Firebase calls the default constructor)
        wins = 0;
        ties = 0;
        losses = 0;
    }

    void updateRecordFromNewScore(int tS, int oS) {
        if (tS > oS) {
            wins++;
        } else if (tS == oS) {
            ties++;
        } else {
            losses++;
        }
    }

    void updateRecordFromRemovedScore(int tS, int oS) {
        if (tS > oS) {
            wins--;
        } else if (tS == oS) {
            ties--;
        } else {
            losses--;
        }
    }
}