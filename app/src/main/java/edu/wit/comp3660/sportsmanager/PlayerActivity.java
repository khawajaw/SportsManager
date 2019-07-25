package edu.wit.comp3660.sportsmanager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;
import edu.wit.comp3660.sportsmanager.DataEntities.Player;

public class PlayerActivity extends AppCompatActivity {

    private Player player;
    private boolean isCreatingPlayer;

    PlayerView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new PlayerView(this, new ImageClicked());
        setContentView(view);

        Bundle extras = getIntent().getExtras();
        int selectedPlayerIndex = extras.getInt("selectedPlayerIndex");
        if (selectedPlayerIndex >= 0) {
            player = LoadedData.get().getCurrentTeam().getRoster().get(selectedPlayerIndex);
            isCreatingPlayer = false;
        }
        if (player != null)
            view.populateData(player);
        else {
            player = new Player();
            isCreatingPlayer = true;
        }

        setSupportActionBar(view.getToolbar());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        menu.removeItem(R.id.add_menu_action);
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
        player.image = view.getImage();
        player.name = view.getName();
        player.jerseyNumber = view.getJerseyNumber();
        player.phoneNumber = view.getPhoneNumber();
        player.height = view.getHeightInInches();
        player.weight = view.getWeight();
        if (isCreatingPlayer) {
            LoadedData.get().getCurrentTeam().getRoster().add(player);
        }
    }

    class ImageClicked implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast t = Toast.makeText(getApplicationContext(), "Clicked on image (we will open camera roll soon)", Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER,0,0);
            t.show();
        }
    }

}
