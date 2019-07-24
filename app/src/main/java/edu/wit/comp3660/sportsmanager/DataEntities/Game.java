package edu.wit.comp3660.sportsmanager.DataEntities;

public class Game {
    private String opponent;
    private String location;
    private String date;
    private String gameTime;
    private boolean away;
    private int teamScore;
    private int opponentScore;
    private boolean played = false;

    public Game(String opponent) {
        this(opponent, "Unknown Location", "No date", "No time", "Home");
    }

    public Game(String opponent, String location, String date, String gameTime, String option) {
        this(opponent, location, date, gameTime, option, 0, 0);
    }

    public Game(String opponent, String location, String date, String gameTime, String option, int teamScore, int opponentScore) {
        this.opponent = opponent;
        this.location = location;
        this.date = date;
        this.gameTime = gameTime;
        if(option.equals("Home")) {
            this.away = false;
        }
        else {
            this.away = true;
        }
        this.teamScore = teamScore;
        this.opponentScore = opponentScore;
    }

    public String getOpponent() {
        return opponent;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getGameTime() {
        return gameTime;
    }

    public boolean isAway() {
        return away;
    }

    public boolean isPlayed() {
        return played;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    public void setScore(int teamScore, int opponentScore) {
        this.teamScore = teamScore;
        this.opponentScore = opponentScore;

        this.played = true;
    }
}
