package edu.wit.comp3660.sportsmanager.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.wit.comp3660.sportsmanager.R;

public class LineupAdapter extends RecyclerView.Adapter<LineupviewHolder>{

    private List<LineupObject> itemList;
    private Context context;

    public LineupAdapter(Context context, List<LineupObject> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public LineupviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lineup_list_item, null);
        LineupviewHolder rcv = new LineupviewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(LineupviewHolder holder, int position) {
        holder.playername.setText(" " + itemList.get(position).getPlayername());
        holder.number.setText(" " + itemList.get(position).getNumber());
        holder.position.setText(" " + itemList.get(position).getPosition());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}