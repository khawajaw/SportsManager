package edu.wit.comp3660.sportsmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.List;

import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;
import edu.wit.comp3660.sportsmanager.DataEntities.Player;
import edu.wit.comp3660.sportsmanager.DataEntities.Team;
import edu.wit.comp3660.sportsmanager.ListAdapters.RosterListAdapter;

public class RosterFragment extends Fragment {

    private RosterListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.roster, container, false);
        Log.v("myRosterFragment", "onCreateViewCalled");

        Team currentTeam = LoadedData.get().getCurrentTeam();

        final List<Player> current_roster = currentTeam.getRoster();
        Player rando = new Player(getActivity());
        rando.name = "Jones";
        current_roster.add(rando);

        adapter = new RosterListAdapter(getActivity(), 0, current_roster);

        ListView listView = rootView.findViewById(R.id.roster_list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startPlayerActivity(i);
            }
        });

        setHasOptionsMenu(true);

        return rootView;
    }

    private void startPlayerActivity(int selectedPlayerIndex) {
        Intent intent = new Intent(getActivity(), PlayerActivity.class);
        intent.putExtra("selectedPlayerIndex", selectedPlayerIndex);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        menu.removeItem(R.id.save_menu_action);
        menu.removeItem(R.id.edit_menu_action);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_menu_action) {
            startPlayerActivity(-1);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && adapter != null)
            adapter.notifyDataSetChanged();
        else
            Log.v("myRosterFragment", "onActivityResult was not OK");
    }

}
