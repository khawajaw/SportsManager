package edu.wit.comp3660.sportsmanager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;

import edu.wit.comp3660.sportsmanager.DataEntities.Game;
import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;
import edu.wit.comp3660.sportsmanager.DataEntities.Player;
import edu.wit.comp3660.sportsmanager.DataEntities.Team;


public class TeamHomeFragment extends Fragment {

    private static final int PICK_IMAGE = 1;
    private ImageView logo;
    private Team team;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        team = LoadedData.get().getCurrentTeam();
        if (team.getName().equals("Leopards") && team.getRoster().isEmpty()) {
            team.getRoster().add(new Player(getContext(), "Jones", "5"));
            team.getRoster().add(new Player(getContext(), "John", "15"));
            team.getRoster().add(new Player(getContext(), "Adam", "18"));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_team_home, container, false);

        logo = rootView.findViewById(R.id.team_logo);
        if(LoadedData.get().getCurrentTeam().getLogo() != null) {
            logo.setImageBitmap(LoadedData.get().getCurrentTeam().getLogo());
        }
        if (team.getLogo() != null)
            logo.setImageBitmap(team.getLogo());
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        TextView text = rootView.findViewById(R.id.team_home_message);
        text.setText(team.toString());

        getTeamData(rootView);

        return rootView;
    }

    private void getTeamData(View root) {
        // team home content

        if(!team.getGames().isEmpty() && team.getNextGame() != null) {
            Game nextGame = team.getNextGame();
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

        if(!team.generateRecordText().equals("")) {
            TextView record = root.findViewById(R.id.record);
            record.setText(team.generateRecordText());
        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), data.getData());
                LoadedData.get().getCurrentTeam().setLogo(bitmap);
                logo.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
