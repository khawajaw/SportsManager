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

import org.w3c.dom.Text;

import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;

public class TeamHomeActivity extends AppCompatActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;
    private Fragment selectedFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();
            Fragment selectedFragment;

            switch (item.getItemId()) {
                case R.id.team_home_nav_button:
                    //if (selectedFragment != null)
                    //    ft.remove(selectedFragment);
                    selectedFragment = new TeamHomeFragment();
                    ft.replace(R.id.fragment, selectedFragment);
                    ft.commit();
                    //textMessage = findViewById(R.id.team_home_message);
                    return true;
                case R.id.roster_nav_button:
                    //if (selectedFragment != null)
                    //    ft.remove(selectedFragment);
                    selectedFragment = new RosterFragment();
                    ft.replace(R.id.fragment, selectedFragment);
                    ft.commit();
                    return true;
                case R.id.lineup_nav_button:
                    selectedFragment = new LineupFragment();
                    ft.replace(R.id.fragment, selectedFragment);
                    ft.commit();
                    return true;
                case R.id.games_nav_button:
                    selectedFragment = new GamesNavFragment();
                    ft.replace(R.id.fragment, selectedFragment);
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
        selectedFragment = new TeamHomeFragment();
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment, selectedFragment);
        ft.commit();
        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

}
