package edu.wit.comp3660.sportsmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.Serializable;

import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;
import edu.wit.comp3660.sportsmanager.DataEntities.Player;

public class PlayerActivity extends AppCompatActivity {

    private Player player;

    PlayerView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new PlayerView(this, null);
        setContentView(view);

        Bundle extras = getIntent().getExtras();
        player = LoadedData.get().getCurrentTeam().getRoster().get(extras.getInt("selectedPlayerId"));
        if (player != null)
            view.populateData(player);

        setSupportActionBar(view.getToolbar());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.save_menu_action:
                savePlayerData();
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.edit_menu_action:
                enterEditMode();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void enterEditMode() {
        view.makeFieldsEditable();
    }

    private void savePlayerData() {
        player.name = view.getName();
        player.jerseyNumber = view.getJerseyNumber();
        player.phoneNumber = view.getPhoneNumber();
        player.height = view.getHeightInInches();
        player.weight = view.getWeight();
    }

}
