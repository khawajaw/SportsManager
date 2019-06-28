package com.example.sportsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sportsmanager.DataEntities.LoadedData;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loadDataFromFirebase(null, null)) {
                    Intent intent = new Intent(LoginActivity.this, TeamSelectActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Username/password does not match or exit", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    //TODO get all the data from firebase based on username/password
    /**
     * Load the data from Firebase into memory (LoadedData class)
     * @return true if username and password exist/match
     */
    private boolean loadDataFromFirebase(String username, String password) {
        LoadedData.createTeam("Test Team", LoadedData.SPORTS[0]);
        LoadedData.createTeam("Test Team 2", LoadedData.SPORTS[1]);

        return true;
    }

}
