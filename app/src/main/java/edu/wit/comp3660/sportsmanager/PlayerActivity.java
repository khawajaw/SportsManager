package edu.wit.comp3660.sportsmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import java.io.Serializable;

public class PlayerActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        EditText name = findViewById(R.id.player_name);
        name.setInputType(EditorInfo.TYPE_NULL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
