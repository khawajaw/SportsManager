package edu.wit.comp3660.sportsmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import edu.wit.comp3660.sportsmanager.DataEntities.Game;
import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;
import edu.wit.comp3660.sportsmanager.DataEntities.Team;
import edu.wit.comp3660.sportsmanager.ListAdapters.GameNavListAdapter;

public class GamesNavFragment extends Fragment {
    GameNavListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.games_nav, container, false);

        Team currentTeam = LoadedData.getCurrentTeam();
        ArrayList<Game> games = currentTeam.getGames();

        adapter = new GameNavListAdapter(getActivity(), 0, games);

        ListView listView = rootView.findViewById(R.id.games_nav_list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LoadedData.currentGameIndex = i;
                EditGameDialogFragment dialogFragment = new EditGameDialogFragment(new DialogCallback());
                dialogFragment.show(getActivity().getSupportFragmentManager(), "EditGameDialog");
            }
        });

        Button addBtn = rootView.findViewById(R.id.addGameBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewGameDialogFragment dialogFragment = new NewGameDialogFragment(new DialogCallback());
                dialogFragment.show(getActivity().getSupportFragmentManager(), "NewGameDialog");
            }
        });

        return rootView;
    }

    class DialogCallback {
        void onGameAdded() {
            adapter.notifyDataSetChanged();
        }
    }
}
