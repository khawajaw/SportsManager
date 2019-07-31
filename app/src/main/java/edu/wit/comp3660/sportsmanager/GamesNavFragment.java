package edu.wit.comp3660.sportsmanager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import edu.wit.comp3660.sportsmanager.DataEntities.Game;
import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;
import edu.wit.comp3660.sportsmanager.DataEntities.Team;
import edu.wit.comp3660.sportsmanager.ListAdapters.GameNavListAdapter;

public class GamesNavFragment extends Fragment {
    private View rootView;
    private GameNavListAdapter adapter;
    private Team currentTeam;
    private ArrayList<Game> games;
    private TextView defaultText;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.games_nav, container, false);

        currentTeam = LoadedData.get().getCurrentTeam();
        games = currentTeam.getGames();

        adapter = new GameNavListAdapter(getActivity(), 0, games);

        listView = rootView.findViewById(R.id.games_nav_list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LoadedData.updateCurrentGameIndex(i);
                EditGameDialogFragment dialogFragment = new EditGameDialogFragment(new GameDialogCallback());
                dialogFragment.show(getActivity().getSupportFragmentManager(), "EditGameDialog");
            }
        });

        listView.setOnItemLongClickListener(new RemoveListItemListener(games, new GameDialogCallback()));

        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        menu.removeItem(R.id.save_menu_action);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add_menu_action:
                NewGameDialogFragment dialogFragment = new NewGameDialogFragment(new GameDialogCallback());
                dialogFragment.show(getActivity().getSupportFragmentManager(), "NewGameDialog");
                break;
            case R.id.edit_menu_action:
                Toast t = Toast.makeText(getContext(), "Long press on a game to remove it", Toast.LENGTH_LONG);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

        currentTeam = LoadedData.get().getCurrentTeam();
        games = currentTeam.getGames();
        defaultText = rootView.findViewById(R.id.gamesText);
        if(games.isEmpty()) {
            defaultText.setVisibility(View.VISIBLE);
        }
        else {
            defaultText.setVisibility(View.INVISIBLE);
        }
    }

    class GameDialogCallback implements DialogCallback {
        public void onAdded() {
            adapter.notifyDataSetChanged();
            defaultText.setVisibility(View.INVISIBLE);
        }

        public void onRemoved(Object game) {
            adapter.notifyDataSetChanged();
            LoadedData.get().getCurrentTeam().removeGameIfPlayed((Game) game);
        }
    }
}
