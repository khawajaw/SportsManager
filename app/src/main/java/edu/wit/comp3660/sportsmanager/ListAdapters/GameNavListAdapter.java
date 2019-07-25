package edu.wit.comp3660.sportsmanager.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import edu.wit.comp3660.sportsmanager.DataEntities.Game;
import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;
import edu.wit.comp3660.sportsmanager.R;

import java.util.List;

public class GameNavListAdapter extends ArrayAdapter<Game> {
    private LayoutInflater mInflater;

    public GameNavListAdapter(Context context, int rid, List<Game> list) {
        super(context, rid, list);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Game game = getItem(position);

        View view = mInflater.inflate(R.layout.games_nav_item, null);

        // home team
        TextView homeName;
        homeName = view.findViewById(R.id.homeTeamName);
        homeName.setText(LoadedData.get().getCurrentTeam().getName());

        TextView homeScore;
        homeScore = view.findViewById(R.id.homeTeamScore);
        homeScore.setText(String.valueOf(game.getTeamScore()));

        // middle
        TextView date;
        date = view.findViewById(R.id.date);
        date.setText(game.getDate());

        TextView separator;
        separator = view.findViewById(R.id.vs);
        if(game.isAway()) {
            separator.setText("@");
        }
        else {
            separator.setText(R.string.game_separator);
        }

        // opponent team
        TextView opponentScore;
        opponentScore = view.findViewById(R.id.awayTeamScore);
        opponentScore.setText(String.valueOf(game.getOpponentScore()));

        TextView opponentName;
        opponentName = view.findViewById(R.id.awayTeamName);
        opponentName.setText(game.getOpponent());

        return view;
    }
}
