package edu.wit.comp3660.sportsmanager.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.wit.comp3660.sportsmanager.R;

public class LineupAdapter extends RecyclerView.Adapter<LineupviewHolder>{

    private List<LineupObject> itemList;
    private Context context;
    private Spinner spinner;

    public LineupAdapter(Context context, List<LineupObject> itemList) {
        this.itemList = itemList;
        this.context = context;
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
    public void onBindViewHolder(LineupviewHolder holder, int position) {
        holder.number.setText(" " + itemList.get(position).getNumber());
        holder.position.setText(" " + itemList.get(position).getPosition());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    private void dropDownPlayers() {
        ArrayList<LineupAdapter.name> names = new ArrayList<>();
        names.add(new LineupAdapter.name("Wajih"));
        names.add(new LineupAdapter.name("Jose"));
        names.add(new LineupAdapter.name("Wes"));

        ArrayAdapter<LineupAdapter.name> adapter =
                new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, names);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

    }

    private class name {
        private String name;


        public name() {
        }

        public name(String name) {
            this.name = name;

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name= name;
        }



        @Override
        public String toString() {
            return name;
        }
    }
}