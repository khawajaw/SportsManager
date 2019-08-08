package edu.wit.comp3660.sportsmanager.DataEntities;

import java.util.ArrayList;

public class Lineup {

    ArrayList<Position> positions;
    ArrayList<String> playerIds;

    Lineup() {
        super();
    }

    Lineup(Sport sport) {
        positions = new ArrayList<>(sport.getPositions());
        playerIds = new ArrayList<>(positions.size());
        for (int i = 0; i < positions.size(); i++)
            playerIds.add("Empty");
    }

    public Position getPosition(int index) {
        return positions.get(index);
    }

    public Player player(int index) {
        return LoadedData.get().getCurrentTeam().findPlayer(playerIds.get(index));
    }
//
//    public void setPlayer(Position getPosition, Player player) {
//        for (LineupEntity e: this)
//            if (e.getPosition == getPosition) {
//                e.changePlayer(player);
//            }
//    }

    public void changePlayer(int index, String playerId) {
        playerIds.set(index, playerId);
    }

    public int size() {
        return positions.size();
    }

}
