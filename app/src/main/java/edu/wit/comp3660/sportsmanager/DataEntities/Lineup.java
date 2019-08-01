package edu.wit.comp3660.sportsmanager.DataEntities;

import java.util.ArrayList;

public class Lineup extends ArrayList<Lineup.LineupEntity> {
    public String name;
    public Sport sport;

    Lineup() {

    }

    public Lineup(Sport sport) {
        this.sport = sport;
        for (Position e:sport.getPositions()) {
            add(new LineupEntity(e));
        }
    }

    public Position getPosition(int index) {
        return super.get(index).position;
    }

    public Player getPlayer(int index) {
        return super.get(index).player;
    }

    public void setPlayer(Position position, Player player) {
        for (LineupEntity e: this)
            if (e.position == position) {
                e.player = player;
            }
    }

    public void setPlayer(int index, Player player) {
        super.get(index).player = player;
    }

    class LineupEntity {
        Position position;
        Player player;

        LineupEntity(Position position) {
            this.position = position;
            player = new Player();
        }
    }
}
