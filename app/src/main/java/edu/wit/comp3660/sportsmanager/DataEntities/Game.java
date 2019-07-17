package edu.wit.comp3660.sportsmanager.DataEntities;

import java.util.Date;

public class Game {
    private Date date;
    private String location;
    private Team opponent;
    private boolean away;
    private int teamScore;
    private int opponentScore;

    public Game(Date date, String location, Team opponent) {
        this(date, location, opponent, false);
    }

    public Game(Date date, String location, Team opponent, boolean away) {
        this(date, location, opponent, away, 0, 0);
    }

    public Game(Date date, String location, Team opponent, boolean away, int teamScore, int opponentScore) {
        this.date = date;
        this.location = location;
        this.opponent = opponent;
        this.away = away;
        this.teamScore = teamScore;
        this.opponentScore = opponentScore;
    }

    public Date getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public Team getOpponent() {
        return opponent;
    }

    public boolean isAway() {
        return away;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }
}
