package edu.wit.comp3660.sportsmanager.ListAdapters;

import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.wit.comp3660.sportsmanager.R;

public class LineupviewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{

    public TextView playername;
    public TextView number;
    public TextView position;

    private SparseBooleanArray selectedItems = new SparseBooleanArray();

    public LineupviewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        playername = (TextView)itemView.findViewById(R.id.playername);
        number = (TextView)itemView.findViewById(R.id.number);
        position = (TextView)itemView.findViewById(R.id.position);
    }

    @Override
    public void onClick(View view) {
        if (selectedItems.get(getAdapterPosition(), false)) {
            selectedItems.delete(getAdapterPosition());
            view.setSelected(false);
        }
        else {
            selectedItems.put(getAdapterPosition(), true);
            view.setSelected(true);
        }
    }
}