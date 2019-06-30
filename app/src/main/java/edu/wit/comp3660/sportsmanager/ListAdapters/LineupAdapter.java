package edu.wit.comp3660.sportsmanager.ListAdapters;
/*
import android.widget.ArrayAdapter;

import android.view.*;
import android.widget.*;
import android.content.*;
import android.app.LauncherActivity.*;
import java.util.List;

public class LineupAdapter extends ArrayAdapter<Lineup> {
    private LayoutInflater mInflater;
    public LineupAdapter(Context context, int rid, List<ListItem> list){
        super(context, rid, list);
        mInflater =
                (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        // Retrieve data
        Lineup item = (Lineup)getItem(position);
        // Use layout file to generate View
        View view = mInflater.inflate(R.layout.list_item, null);
        // Set user name
        TextView name;
        name = (TextView)view.findViewById(R.id.name);
        name.setText(item.name);
        // Set position
        TextView comment;
        comment = (TextView) view.findViewById(R.id.position);
        comment.setText(item.position);
        return view;
    }
} */