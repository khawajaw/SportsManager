package edu.wit.comp3660.sportsmanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

import edu.wit.comp3660.sportsmanager.DataEntities.Player;
import edu.wit.comp3660.sportsmanager.DataEntities.Position;
import edu.wit.comp3660.sportsmanager.DataEntities.Sport;
import edu.wit.comp3660.sportsmanager.PlayerActivity.ImageClicked;

class PlayerView extends ConstraintLayout {

    private Toolbar toolbar;
    private ImageView image;
    private EditText name;
    private EditText jerseyNumber;
    private EditText phoneNumber;
    private Spinner height_feet;
    private Spinner height_inches;
    private EditText weight;
    private Spinner preferredPosition;

    public PlayerView(Context context, ImageClicked imageClickListener) {
        super(context, null);
        inflate(context, R.layout.activity_player, this);

        toolbar = findViewById(R.id.toolbar);
        image = findViewById(R.id.player_avatar);
        name = findViewById(R.id.player_name);
        jerseyNumber = findViewById(R.id.player_number);
        phoneNumber = findViewById(R.id.player_phone);
        height_feet = findViewById(R.id.spinner_player_height_ft);
        height_inches = findViewById(R.id.spinner_player_height_in);
        weight = findViewById(R.id.text_player_weight);
        preferredPosition = findViewById(R.id.spinner_preferred_position);

        image.setOnClickListener(imageClickListener);
    }

    public void populateData(Player player) {
        name.setInputType(EditorInfo.TYPE_NULL);
        name.setBackground(null);
        phoneNumber.setInputType(EditorInfo.TYPE_NULL);
        phoneNumber.setBackground(null);
        jerseyNumber.setInputType(EditorInfo.TYPE_NULL);
        jerseyNumber.setBackground(null);

        image.setImageBitmap(player.image);
        name.setText(player.name);
        jerseyNumber.setText(player.jerseyNumber);
        phoneNumber.setText(player.phoneNumber);
        height_feet.setSelection((player.height/12)-4);
        height_inches.setSelection(player.height%12);
        weight.setText(player.getWeightText());
        preferredPosition.setSelection(player.getPreferredPositionIndex());
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public Bitmap getImage() {
        return ((BitmapDrawable) image.getDrawable()).getBitmap();
    }

    public String getJerseyNumber() {
        return jerseyNumber.getText().toString();
    }

    public String getName() {
        return name.getText().toString();
    }

    public String getPhoneNumber() {
        return phoneNumber.getText().toString();
    }

    public int getHeightInInches() {
        return Integer.parseInt(height_feet.getSelectedItem().toString())*12
                + Integer.parseInt(height_inches.getSelectedItem().toString());
    }

    public int getWeight() {
        String text = weight.getText().toString();
        if (!text.equals(""))
            return Integer.parseInt(text);
        else return 0;
    }

    public Position getPreferredPosition() {
        Position selected = (Position) preferredPosition.getSelectedItem();
        if (selected == Position.NONE)
            return null;
        else return selected;
    }

    public void setPositionSpinnerAdapter(Sport sport) {
        ArrayList<Position> positions = new ArrayList<>();
        positions.add(Position.NONE);
        ArrayAdapter<Position> dropdownAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                positions);
        dropdownAdapter.addAll(sport.getPositions());
        preferredPosition.setAdapter(dropdownAdapter);
    }

    public void makeFieldsEditable() {
        name.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PERSON_NAME);
        name.setBackgroundResource(android.R.drawable.edit_text);
        jerseyNumber.setInputType(EditorInfo.TYPE_NUMBER_VARIATION_NORMAL);
        jerseyNumber.setBackgroundResource(android.R.drawable.edit_text);
        phoneNumber.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        phoneNumber.setBackgroundResource(android.R.drawable.edit_text);


    }

    public void updateImage(Bitmap bitmap) {
        image.setImageBitmap(bitmap);
    }
}
