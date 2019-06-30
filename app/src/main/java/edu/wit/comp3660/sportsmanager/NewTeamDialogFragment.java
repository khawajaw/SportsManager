package edu.wit.comp3660.sportsmanager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;
import edu.wit.comp3660.sportsmanager.DataEntities.Sport;

public class NewTeamDialogFragment extends DialogFragment {

    private View view;
    private Spinner dropdown;
    private EditText team_name_input;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_new_team_dialog, null);
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("Create New Team", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LoadedData.createTeam(
                                team_name_input.getText().toString(),
                                (Sport) dropdown.getSelectedItem());
                    }
                })
                .setNegativeButton("Cancel", null);

        createDropdown();
        team_name_input = view.findViewById(R.id.team_name_input);
        team_name_input.addTextChangedListener(new TeamNameTextWatcher());

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

    private void createDropdown() {
        dropdown = view.findViewById(R.id.spinner);
        ArrayAdapter<Sport> dropdownAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                LoadedData.SPORTS);
        //dropdownAdapter.insert(new Sport("Select Sport..."), 0);
        dropdown.setAdapter(dropdownAdapter);
    }

    private class TeamNameTextWatcher implements TextWatcher {

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
