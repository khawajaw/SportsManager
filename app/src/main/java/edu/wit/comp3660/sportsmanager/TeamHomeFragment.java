package edu.wit.comp3660.sportsmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;

import edu.wit.comp3660.sportsmanager.DataEntities.Game;
import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;
import edu.wit.comp3660.sportsmanager.DataEntities.Team;


public class TeamHomeFragment extends Fragment {

    private static final int PICK_IMAGE = 1;
    ImageView logo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_team_home, container, false);

        logo = rootView.findViewById(R.id.team_logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        TextView text = rootView.findViewById(R.id.team_home_message);
        text.setText(LoadedData.get().getCurrentTeam().toString());

        getTeamData(rootView);

        return rootView;
    }

    private void getTeamData(View root) {
        // team home content
        Team selected = LoadedData.get().getCurrentTeam();

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
