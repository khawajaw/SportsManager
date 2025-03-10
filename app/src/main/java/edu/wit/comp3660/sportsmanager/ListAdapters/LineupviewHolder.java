package edu.wit.comp3660.sportsmanager.ListAdapters;

import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.wit.comp3660.sportsmanager.R;

public class LineupviewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{

    public Spinner playername;
    public ImageView image;
    public TextView number;
    public TextView position;

    private SparseBooleanArray selectedItems = new SparseBooleanArray();

    public LineupviewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        playername = (Spinner)itemView.findViewById(R.id.playerName);
        number = (TextView)itemView.findViewById(R.id.number);
        position = (TextView)itemView.findViewById(R.id.position);
        image = itemView.findViewById(R.id.player_image);
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