package com.example.sportsmanager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sportsmanager.DataEntities.Player;

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
