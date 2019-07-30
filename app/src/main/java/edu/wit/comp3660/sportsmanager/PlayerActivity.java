package edu.wit.comp3660.sportsmanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

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
            enterEditMode();
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
        view.setPositionSpinnerAdapter(LoadedData.get().getCurrentTeam().getSport());
    }

    private void savePlayerData() {
        player.image = view.getImage();
        player.name = view.getName();
        player.jerseyNumber = view.getJerseyNumber();
        player.phoneNumber = view.getPhoneNumber();
        player.height = view.getHeightInInches();
        player.weight = view.getWeight();
        player.preferredPosition = view.getPreferredPosition();
        if (isCreatingPlayer) {
            LoadedData.get().getCurrentTeam().getRoster().add(player);
        }
    }

    private static final int PICK_IMAGE = 1;

    class ImageClicked implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast t = Toast.makeText(getApplicationContext(), "Clicked on image (we will open camera roll soon)", Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER,0,0);
            t.show();
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                player.image = bitmap;
                view.updateImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
