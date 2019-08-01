package edu.wit.comp3660.sportsmanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.InputType;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

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
    private TextView feet_label;
    private Spinner height_inches;
    private TextView inches_label;
    private EditText weight;
    private TextView preferredPosition_label;
    private Spinner preferredPosition;
    private EditText playerNotes;

    public PlayerView(Context context, ImageClicked imageClickListener) {
        super(context, null);
        inflate(context, R.layout.activity_player, this);

        toolbar = findViewById(R.id.toolbar);
        image = findViewById(R.id.player_avatar);
        name = findViewById(R.id.player_name);
        jerseyNumber = findViewById(R.id.player_number);
        phoneNumber = findViewById(R.id.player_phone);
        height_feet = findViewById(R.id.spinner_player_height_ft);
        feet_label = findViewById(R.id.feet_label);
        height_inches = findViewById(R.id.spinner_player_height_in);
        inches_label = findViewById(R.id.inches_label);
        weight = findViewById(R.id.text_player_weight);
        preferredPosition_label = findViewById(R.id.label_preferred_position);
        preferredPosition = findViewById(R.id.spinner_preferred_position);
        playerNotes = findViewById(R.id.player_notes);

        image.setOnClickListener(imageClickListener);
    }

    public void populateData(Player player) {
        makeFieldsReadOnly(player);

        image.setImageBitmap(player.image);
        name.setText(player.name);
        jerseyNumber.setText(player.jerseyNumber);
        phoneNumber.setText(player.phoneNumber);
        height_feet.setSelection((player.height/12)-4);
        height_inches.setSelection(player.height%12);
        weight.setText(String.valueOf(player.weight));
        playerNotes.setText(player.notes);
    }

    private void makeFieldsReadOnly(Player player) {
        image.setClickable(false);
        name.setInputType(EditorInfo.TYPE_NULL);
        name.setBackground(null);
        phoneNumber.setInputType(EditorInfo.TYPE_NULL);
        phoneNumber.setBackground(null);
        jerseyNumber.setInputType(EditorInfo.TYPE_NULL);
        jerseyNumber.setBackground(null);
        weight.setInputType(EditorInfo.TYPE_NULL);
        weight.setBackground(null);
        height_inches.setVisibility(GONE);
        height_feet.setVisibility(GONE);
        feet_label.setText(player.getHeightText());
        inches_label.setVisibility(GONE);

        String preferredPositionText = getContext().getString(R.string.preferred_position)+"  "
                + (player.preferredPosition == null ? "Not set" : player.preferredPosition);
        preferredPosition_label.setText(preferredPositionText);
        preferredPosition.setVisibility(INVISIBLE);
        playerNotes.setInputType(EditorInfo.TYPE_NULL);
    }

    public void makeFieldsEditable() {
        image.setClickable(true);
        name.setInputType(EditorInfo.TYPE_TEXT_VARIATION_PERSON_NAME);
        name.setBackgroundResource(android.R.drawable.edit_text);
        jerseyNumber.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        jerseyNumber.setBackgroundResource(android.R.drawable.edit_text);
        phoneNumber.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        phoneNumber.setBackgroundResource(android.R.drawable.edit_text);
        preferredPosition.setVisibility(VISIBLE);
        preferredPosition_label.setText(R.string.preferred_position);
        weight.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        weight.setBackgroundResource(android.R.drawable.edit_text);
        height_inches.setVisibility(VISIBLE);
        height_feet.setVisibility(VISIBLE);
        inches_label.setVisibility(VISIBLE);
        feet_label.setText(R.string.feet);
        playerNotes.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_FLAG_MULTI_LINE);
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

    public String getNotesText() {
        return playerNotes.getText().toString();
    }

    public void setPositionSpinnerAdapter(Sport sport, Player player) {
        ArrayList<Position> positions = new ArrayList<>();
        positions.add(Position.NONE);
        ArrayAdapter<Position> dropdownAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                positions);
        dropdownAdapter.addAll(sport.getPositions());
        preferredPosition.setAdapter(dropdownAdapter);
        preferredPosition.setSelection(player.getPreferredPositionIndex());
    }

    public void updateImage(Bitmap bitmap) {
        image.setImageBitmap(bitmap);
    }
}
