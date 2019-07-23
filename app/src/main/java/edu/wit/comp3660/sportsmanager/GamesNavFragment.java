package edu.wit.comp3660.sportsmanager;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import edu.wit.comp3660.sportsmanager.DataEntities.Game;
import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;
import edu.wit.comp3660.sportsmanager.DataEntities.Team;
import edu.wit.comp3660.sportsmanager.ListAdapters.GameNavListAdapter;


public class GamesNavFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.games_nav, container, false);

        Team currentTeam = LoadedData.getCurrentTeam();
        ArrayList<Game> games = currentTeam.getGames();
        if(!games.isEmpty()) {
            rootView.findViewById(R.id.default_games_msg).setVisibility(View.GONE);
        }

        GameNavListAdapter adapter = new GameNavListAdapter(getActivity(), 0, games);

        ListView listView = rootView.findViewById(R.id.games_nav_list_view);
        listView.setAdapter(adapter);

        Button addBtn = rootView.findViewById(R.id.addGameBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment,new AddGameDialogFragment());
                fr.commit();

            }
        });

        return rootView;
    }


}
