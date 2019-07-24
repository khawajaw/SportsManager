package edu.wit.comp3660.sportsmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.wit.comp3660.sportsmanager.DataEntities.Game;
import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;
import edu.wit.comp3660.sportsmanager.DataEntities.Team;


public class TeamHomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_team_home, container, false);
        TextView text = rootView.findViewById(R.id.team_home_message);
        text.setText(LoadedData.getCurrentTeam().toString());

        getTeamData(rootView);

        return rootView;
    }

    private void getTeamData(View root) {
        // team home content
        Team selected = LoadedData.getCurrentTeam();

        if(!selected.getGames().isEmpty() && selected.getNextGame() != null) {
            Game nextGame = selected.getNextGame();
            String vs;
            if(nextGame.isAway()) {
                vs = " @ ";
            }
            else {
                vs = " vs. ";
            }

            String main = nextGame.getDate() + " at " + nextGame.getGameTime() + vs + nextGame.getOpponent();
            String location = nextGame.getLocation();

            TextView nextGameTextViewMain = root.findViewById(R.id.next_game_main);
            TextView nextGameTextViewLocation = root.findViewById(R.id.next_game_location);

            nextGameTextViewMain.setText(main);
            nextGameTextViewLocation.setText(location);
        }

        if(!selected.getRecord().equals("")) {
            TextView record = root.findViewById(R.id.record);
            record.setText(selected.getRecord());
        }
    }

}
