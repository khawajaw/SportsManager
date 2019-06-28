package com.example.sportsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.sportsmanager.DataEntities.LoadedData;
import com.example.sportsmanager.DataEntities.Team;

public class TeamSelectActivity extends AppCompatActivity {

    private ListView teamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_select);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        teamList = findViewById(R.id.sportsList);

        ArrayAdapter<Team> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, LoadedData.getTeams());

        teamList.setAdapter(adapter);
        teamList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LoadedData.currentTeamIndex = position;
                Intent intent = new Intent(TeamSelectActivity.this, TeamHomeActivity.class);
                startActivity(intent);
            }
        });


        Button createTeam = findViewById(R.id.create_team_button);
        createTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Make this open a dialog to input team name/sport. Just go to player activity for now
                //Intent intent = new Intent(TeamSelectActivity.this, PlayerActivity.class);
                //startActivity(intent);
                NewTeamDialogFragment dialogFragment = new NewTeamDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "NewTeamDialog");


            }
        });
    }


}
