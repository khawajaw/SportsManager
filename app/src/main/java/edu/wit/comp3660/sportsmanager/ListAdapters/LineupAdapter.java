package edu.wit.comp3660.sportsmanager.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;
import edu.wit.comp3660.sportsmanager.DataEntities.Player;
import edu.wit.comp3660.sportsmanager.DataEntities.Position;
import edu.wit.comp3660.sportsmanager.R;

public class LineupAdapter extends RecyclerView.Adapter<LineupviewHolder>{

    private List<Position> positionList;
    private ArrayList<Player> players;
    private Context context;
    private Spinner spinner;

    public LineupAdapter(Context context, List<Position> positionList) {
        this.positionList = positionList;
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
        //holder.number.setText(" " + positionList.get(position).getNumber());
        holder.position.setText(positionList.get(position).toString());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int index, long id) {
                Player selectedPlayer = players.get(index);
                holder.number.setText("#"+selectedPlayer.jerseyNumber);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                holder.number.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.positionList.size();
    }

    private void dropDownPlayers() {
        ArrayAdapter<Player> adapter =
                new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, players);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

}