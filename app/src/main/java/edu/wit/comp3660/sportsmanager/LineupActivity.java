package edu.wit.comp3660.sportsmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

public class LineupActivity extends AppCompatActivity {
    private final String TAG = "myApp"; //
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String [] testString = new String[10];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineup);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        for(int i = 0; i<testString.length; i++){
            /**
             * call array filled with team members and print them
             */
            adapter.add("sample text");
        }
        ListView listView = (ListView)findViewById(R.id.ListView01);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()



    {
        @Override
        public void onItemClick (AdapterView <?> parent,
                View view,
                int position,
                long id){
            //On click should lead to the player activity which will then further expand on
            // player info
           // Intent intent = new Intent(LineupActivity.this, PlayerActivity.class);
           // startActivity(intent);
    }
    });
}}
