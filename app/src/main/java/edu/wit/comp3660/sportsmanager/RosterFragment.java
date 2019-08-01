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
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;
import edu.wit.comp3660.sportsmanager.DataEntities.Player;
import edu.wit.comp3660.sportsmanager.DataEntities.Team;
import edu.wit.comp3660.sportsmanager.ListAdapters.RosterListAdapter;

public class RosterFragment extends Fragment {

    private View rootView;
    private RosterListAdapter adapter;
    private ListView listView;
    private Team currentTeam;
    private List<Player> current_roster;
    private DialogCallback callback;
    TextView defaultText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.roster, container, false);
        Log.v("myRosterFragment", "onCreateViewCalled");

        currentTeam = LoadedData.get().getCurrentTeam();
        current_roster = currentTeam.getRoster();

        adapter = new RosterListAdapter(getActivity(), 0, current_roster);

        listView = rootView.findViewById(R.id.roster_list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startPlayerActivity(i);
            }
        });

        callback = new RosterDialogCallback();
        listView.setOnItemLongClickListener(new RemoveListItemListener(current_roster, callback));

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

    public void refreshToTop() {
        adapter.notifyDataSetChanged();
        listView.smoothScrollToPosition(0);
    }

    @Override
    public void onResume() {
        super.onResume();

        currentTeam = LoadedData.get().getCurrentTeam();
        current_roster = currentTeam.getRoster();
        defaultText = rootView.findViewById(R.id.defaultText);
        if(current_roster.isEmpty()) {
            defaultText.setVisibility(View.VISIBLE);
        }
        else {
            defaultText.setVisibility(View.INVISIBLE);
        }
    }

    class RosterDialogCallback implements DialogCallback {

        @Override
        public void onAdded() {
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onRemoved(Object entity) {
            adapter.notifyDataSetChanged();
            if(current_roster.isEmpty()) {
                defaultText.setVisibility(View.VISIBLE);
            }
        }
    }
}
