package edu.wit.comp3660.sportsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;

public class LoginActivity extends AppCompatActivity {

    private ProgressBar loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_button = findViewById(R.id.login_button);
        loadingBar = findViewById(R.id.progressBar);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loadDataFromFirebase("username", null)) {
                    LoadedData.loggedInUser = "username";
                    //wait for data to load
                } else {
                    Toast.makeText(getApplicationContext(), "Username/password does not match or exit", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    public void notifyDataLoaded() {
        Intent intent = new Intent(LoginActivity.this, TeamSelectActivity.class);
        startActivity(intent);
        loadingBar.setVisibility(View.INVISIBLE);
    }

    //TODO get all the data from firebase based on username/password
    /**
     * Load the data from Firebase into memory (LoadedData class)
     * @return true if username and password exist/match
     */
    private boolean loadDataFromFirebase(String username, String password) {
        boolean useFirebase = false; //switch to true if you want to test Firebase
        if (useFirebase){
            loadingBar.setVisibility(View.VISIBLE);
            LoadedData.fetchDataFromFirebase(username, this);
        }
        else {
            LoadedData.createTeam("Local Test Team");
            LoadedData.createTeam("Local Test Team 2");
            notifyDataLoaded();
        }
        return true;
    }

}
