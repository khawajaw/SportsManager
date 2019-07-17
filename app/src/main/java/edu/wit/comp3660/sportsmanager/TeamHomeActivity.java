package edu.wit.comp3660.sportsmanager;

import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.TextView;

import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;

public class TeamHomeActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fm;
            FragmentTransaction ft;
            Fragment selectedFragment;

            switch (item.getItemId()) {
                case R.id.team_home_nav_button:
                    mTextMessage.setText(LoadedData.getCurrentTeam().toString());
                    return true;
                case R.id.roster_nav_button:
                    fm = getSupportFragmentManager();
                    ft = fm.beginTransaction();
                    selectedFragment = new RosterFragment();
                    ft.replace(R.id.team_home_frame, selectedFragment);
                    ft.commit();
                    return true;
                case R.id.lineup_nav_button:
                    fm = getSupportFragmentManager();
                    ft = fm.beginTransaction();
                    selectedFragment = new LineupFragment();
                    ft.replace(R.id.team_home_frame, selectedFragment);
                    ft.commit();
                    return true;
                case R.id.games_nav_button:
                    fm = getSupportFragmentManager();
                    ft = fm.beginTransaction();
                    selectedFragment = new GamesNavFragment();
                    ft.replace(R.id.team_home_frame, selectedFragment);
                    ft.commit();
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
