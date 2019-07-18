package edu.wit.comp3660.sportsmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.Serializable;

import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;
import edu.wit.comp3660.sportsmanager.DataEntities.Player;

public class PlayerActivity extends AppCompatActivity implements Serializable {

    Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Bundle extras = getIntent().getExtras();
        player = LoadedData.getCurrentTeam().getRoster().get(extras.getInt("selectedPlayerId"));

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        EditText name = findViewById(R.id.player_name);
        name.setInputType(EditorInfo.TYPE_NULL);
        name.setText(player.name);

        ImageView image = findViewById(R.id.player_avatar);
        image.setImageBitmap(player.image);

        //EditText height = findViewById(R.id.player_height);
        //EditText weight = findViewById(R.id.player_weight);
        //height.setText(player.getHeightText());
        //height.setText(player.getWeightText());

        EditText number = findViewById(R.id.player_number);
        number.setText(player.number);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
