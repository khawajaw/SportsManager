package edu.wit.comp3660.sportsmanager;

import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;
import edu.wit.comp3660.sportsmanager.DataEntities.Player;
import edu.wit.comp3660.sportsmanager.DataEntities.Team;
import edu.wit.comp3660.sportsmanager.ListAdapters.RosterListAdapter;

import java.io.Serializable;
import java.util.List;

public class RosterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.roster, container, false);

        // get selected team somehow
        Team currentTeam = LoadedData.getCurrentTeam();

        final List<Player> current_roster = currentTeam.getRoster();
        Player rando = new Player();
        rando.name = "Randy";
        current_roster.add(rando);

        RosterListAdapter adapter = new RosterListAdapter(getActivity(), 0, current_roster);

        ListView listView = rootView.findViewById(R.id.roster_list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                intent.putExtra("selectedPlayerId", i);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
