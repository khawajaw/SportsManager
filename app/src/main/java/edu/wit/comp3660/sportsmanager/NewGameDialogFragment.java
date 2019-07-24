package edu.wit.comp3660.sportsmanager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;

public class NewGameDialogFragment extends DialogFragment {

    private View view;
    private RadioGroup rGroup;
    private GamesNavFragment.DialogCallback callback;

    NewGameDialogFragment(GamesNavFragment.DialogCallback dialogCallback) {
        callback = dialogCallback;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_new_game_dialog, null);

        final EditText opponentName = view.findViewById(R.id.opponent_name_input);
        final EditText gameLocation = view.findViewById(R.id.game_location_input);
        final DatePicker dPicker = view.findViewById(R.id.game_date_input);
        final TimePicker tPicker = view.findViewById(R.id.game_time_input);
        rGroup = view.findViewById(R.id.away_or_home);

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("Create New Game", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String gameDate = dPicker.getYear() + "-" + (dPicker.getMonth() + 1) + "-" + dPicker.getDayOfMonth();
                        String gameTime = tPicker.getHour() + ":" + tPicker.getMinute();

                        int selectedButton = rGroup.getCheckedRadioButtonId();
                        RadioButton rButton = view.findViewById(selectedButton);
                        LoadedData.createGame(
                                opponentName.getText().toString(),
                                gameLocation.getText().toString(),
                                gameDate,
                                gameTime,
                                rButton.getText().toString());
                        callback.onGameAdded();
                    }
                })
                .setNegativeButton("Cancel", null);

        opponentName.addTextChangedListener(new GameTextWatcher());

        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v("myApp", "show()");
        setEnabledPositiveButton(false);
    }

    public void setEnabledPositiveButton(boolean enable) {
        ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(enable);
    }

    private class GameTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            setEnabledPositiveButton(!TextUtils.isEmpty(s));
        }
    }
}
