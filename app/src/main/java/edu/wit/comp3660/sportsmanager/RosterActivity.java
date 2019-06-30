package edu.wit.comp3660.sportsmanager;

import android.graphics.Bitmap;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import edu.wit.comp3660.sportsmanager.DataEntities.Player;

import java.util.ArrayList;
import java.util.List;

public class RosterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Bitmap playerImage;
        //playerImage = BitmapFactory.decodeResource(getResources(), R.drawable.player_image);

        List<Player> list = new ArrayList<>();


    }
}
