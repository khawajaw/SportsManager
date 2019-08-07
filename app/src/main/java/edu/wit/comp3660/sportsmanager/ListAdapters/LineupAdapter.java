package edu.wit.comp3660.sportsmanager.ListAdapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.wit.comp3660.sportsmanager.DataEntities.Lineup;
import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;
import edu.wit.comp3660.sportsmanager.DataEntities.Player;
import edu.wit.comp3660.sportsmanager.R;

public class LineupAdapter extends RecyclerView.Adapter<LineupviewHolder>{

    private Lineup lineup;
    private ArrayList<Player> players;
    private Context context;
    private Spinner spinner;

    public LineupAdapter(Context context, Lineup lineupList) {
        this.lineup = lineupList;
        this.context = context;
        players = LoadedData.get().getCurrentTeam().getRoster();
    }

    @Override
    public LineupviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lineup_list_item, null);

        spinner = layoutView.findViewById(R.id.playerName);
        dropDownPlayers();

        LineupviewHolder rcv = new LineupviewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final LineupviewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        holder.position.setText(lineup.position(position).toString());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int index, long id) {
                if (index > 0) {
                    Player selectedPlayer = players.get(index-1);
                    lineup.changePlayer(position, selectedPlayer);
                    if (!selectedPlayer.jerseyNumber.equals("")) {
                        holder.number.setVisibility(View.VISIBLE);
                        holder.number.setText("#"+selectedPlayer.jerseyNumber);
                    }
                    if (selectedPlayer.playerImage() != null) {
                        holder.image.setVisibility(View.VISIBLE);
                        holder.image.setImageBitmap(selectedPlayer.playerImage());
                    }
                } else {
                    lineup.changePlayer(position, null);
                    view.setBackgroundColor(Color.RED);
                    onNothingSelected(parent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                holder.number.setVisibility(View.INVISIBLE);
                holder.image.setVisibility(View.INVISIBLE);
            }
        });
        Player correspondingPlayer = lineup.player(position);
        if (lineup.player(position) != null) {
            spinner.setSelection(((ArrayAdapter<Player>) spinner.getAdapter()).getPosition(correspondingPlayer));
        }
    }

    @Override
    public int getItemCount() {
        return this.lineup.size();
    }

    private void dropDownPlayers() {
        ArrayAdapter<Player> adapter =
                new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, new ArrayList<Player>());
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        adapter.add(new Player());
        adapter.addAll(players);
        spinner.setAdapter(adapter);
    }

}