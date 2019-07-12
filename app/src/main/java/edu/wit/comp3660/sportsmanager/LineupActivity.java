package edu.wit.comp3660.sportsmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

import edu.wit.comp3660.sportsmanager.ListAdapters.LineupAdapter;
import edu.wit.comp3660.sportsmanager.ListAdapters.LineupObject;

public class LineupActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private LineupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lineup);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(LineupActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        List<LineupObject> posts = returnListItems();

        adapter = new LineupAdapter(LineupActivity.this, posts);
        recyclerView.setAdapter(adapter);
    }

    private List<LineupObject> returnListItems(){
        List<LineupObject> items = new ArrayList<LineupObject>();
        items.add(new LineupObject("Wajih Khawaja", "ST", "1"));
        items.add(new LineupObject("Wes Brimeyer", "ST", "2"));
        items.add(new LineupObject("Jose Fossi", "ST", "3"));
        return items;
    }
}