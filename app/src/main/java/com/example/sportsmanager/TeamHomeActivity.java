package com.example.sportsmanager;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.sportsmanager.DataEntities.LoadedData;

public class TeamHomeActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.team_home_nav_button:
                    mTextMessage.setText(LoadedData.getCurrentTeam().toString());
                    return true;
                case R.id.roster_nav_button:
                    mTextMessage.setText(R.string.players);
                    return true;
                case R.id.lineup_nav_button:
                    mTextMessage.setText(R.string.lineup);
                    return true;
                case R.id.games_nav_button:
                    mTextMessage.setText(R.string.games);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        mTextMessage.setText(LoadedData.getCurrentTeam().toString());
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

}
