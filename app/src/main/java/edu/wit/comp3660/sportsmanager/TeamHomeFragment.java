package edu.wit.comp3660.sportsmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;


public class TeamHomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_team_home, container, false);
        TextView text = rootView.findViewById(R.id.team_home_message);
        text.setText(LoadedData.getCurrentTeam().toString());

        return rootView;
    }
}
