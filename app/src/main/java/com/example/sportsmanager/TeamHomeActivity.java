package com.example.sportsmanager;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

public class TeamHomeActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.team_home_nav_button:
                    mTextMessage.setText(R.string.team_home);
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

    //test commit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
