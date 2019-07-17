package edu.wit.comp3660.sportsmanager;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import edu.wit.comp3660.sportsmanager.ListAdapters.LineupAdapter;
import edu.wit.comp3660.sportsmanager.ListAdapters.LineupObject;

public class LineupFragment extends Fragment {

    private final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private LineupAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lineup, container, false);

        recyclerView = rootView.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        List<LineupObject> posts = returnListItems();

        adapter = new LineupAdapter(getActivity(), posts);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private List<LineupObject> returnListItems(){
        List<LineupObject> items = new ArrayList<LineupObject>();
        items.add(new LineupObject("Wajih Khawaja", "ST", "1"));
        items.add(new LineupObject("Wes Brimeyer", "ST", "2"));
        items.add(new LineupObject("Jose Fossi", "ST", "3"));
        return items;
    }
}