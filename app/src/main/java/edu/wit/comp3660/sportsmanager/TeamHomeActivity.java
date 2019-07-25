package edu.wit.comp3660.sportsmanager;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TeamHomeActivity extends AppCompatActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;
    private Fragment selectedFragment;
    private ActionBar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();

            switch (item.getItemId()) {
                case R.id.team_home_nav_button:
                    if (!(selectedFragment instanceof TeamHomeFragment)) {
                        selectedFragment = new TeamHomeFragment();
                        ft.replace(R.id.fragment, selectedFragment);
                        ft.commit();
                        toolbar.setTitle("Team Home");
                    }
                    return true;
                case R.id.roster_nav_button:
                    if (selectedFragment instanceof RosterFragment)
                        ((RosterFragment) selectedFragment).refreshToTop();
                    else {
                        selectedFragment = new RosterFragment();
                        ft.replace(R.id.fragment, selectedFragment);
                        ft.commit();
                        toolbar.setTitle("Roster");
                    }
                    return true;
                case R.id.lineup_nav_button:
                    if (!(selectedFragment instanceof LineupFragment)) {
                        selectedFragment = new LineupFragment();
                        ft.replace(R.id.fragment, selectedFragment);
                        ft.commit();
                        toolbar.setTitle("Current Lineup");
                    }
                    return true;
                case R.id.games_nav_button:
                    if (!(selectedFragment instanceof GamesNavFragment)) {
                        selectedFragment = new GamesNavFragment();
                        ft.replace(R.id.fragment, selectedFragment);
                        ft.commit();
                        toolbar.setTitle("Games");
                    }
                    return true;
            }
            fm = null;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_home);
        if (selectedFragment == null) { //only do this stuff this Activity doesn't already exist
            selectedFragment = new TeamHomeFragment();
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.fragment, selectedFragment);
            ft.commit();
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        toolbar = getSupportActionBar();
    }

}
