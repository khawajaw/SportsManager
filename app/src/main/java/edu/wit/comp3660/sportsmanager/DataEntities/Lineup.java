package edu.wit.comp3660.sportsmanager.DataEntities;

import java.util.ArrayList;

public class Lineup extends ArrayList<LineupEntity>  {

    Lineup() {
        super();
    }

    public Lineup(Sport sport) {
        super(sport.getPositions().size());
        for (Position e:sport.getPositions()) {
            add(new LineupEntity(e));
        }
    }

    public Position position(int index) {
        return super.get(index).position;
    }

    public Player player(int index) {
        return super.get(index).player();
    }
//
//    public void setPlayer(Position position, Player player) {
//        for (LineupEntity e: this)
//            if (e.position == position) {
//                e.changePlayer(player);
//            }
//    }

    public void changePlayer(int index, Player player) {
        super.get(index).changePlayer(player);
    }

}

class LineupEntity {
    Position position;
    private Player player;
    String playerId;

    LineupEntity(){}

    LineupEntity(Position position) {
        this.position = position;
        player = new Player();
        playerId = "";
    }

    LineupEntity(Position position, String playerId) {
        this.position = position;
        this.playerId = playerId;
    }


    Player player() {
        if (player == null)
            player = LoadedData.get().getCurrentTeam().findPlayer(playerId);
        return player;
    }

    void changePlayer(Player p) {
        player = p;
        playerId = player().name;
    }
}
