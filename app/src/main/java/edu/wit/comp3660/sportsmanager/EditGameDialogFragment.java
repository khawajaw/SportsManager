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
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;

public class EditGameDialogFragment extends DialogFragment {

    private View view;
    private DialogCallback callback;
    private EditText teamScore;
    private EditText opponentScore;

    EditGameDialogFragment(DialogCallback dialogCallback) {
        callback = dialogCallback;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_edit_game_dialog, null);

        teamScore = view.findViewById(R.id.team_score);
        opponentScore = view.findViewById(R.id.opponent_score);

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LoadedData.get().getCurrentTeam().editGame(teamScore.getText().toString(), opponentScore.getText().toString());
                        callback.onAdded();
                    }
                })
                .setNegativeButton("Cancel", null);

        GameTextWatcher watcher = new GameTextWatcher();
        teamScore.addTextChangedListener(watcher);
        opponentScore.addTextChangedListener(watcher);

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
            Log.v("MyApp", teamScore.getText().toString());
            setEnabledPositiveButton(!TextUtils.isEmpty(teamScore.getText().toString())
                    && !TextUtils.isEmpty(opponentScore.getText().toString()) );
        }
    }
}
