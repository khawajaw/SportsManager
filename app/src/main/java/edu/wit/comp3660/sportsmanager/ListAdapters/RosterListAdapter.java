package edu.wit.comp3660.sportsmanager.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.wit.comp3660.sportsmanager.DataEntities.Player;
import edu.wit.comp3660.sportsmanager.R;

public class RosterListAdapter extends ArrayAdapter<Player> {
    private LayoutInflater mInflater;

    public RosterListAdapter(Context context, int rid, List<Player> list) {
        super(context, rid, list);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Player player = getItem(position);

        View view = mInflater.inflate(R.layout.roster_player_item, null);

        // set player image
        ImageView playerImage;
        playerImage = view.findViewById(R.id.player_image);
        if (player.avatarIsSet) //otherwise use default avatar
            playerImage.setImageBitmap(player.playerImage());

        //set player number
        TextView playerNumber = view.findViewById(R.id.player_number);
        if (player.jerseyNumber.equals("")) playerNumber.setVisibility(View.INVISIBLE);
        playerNumber.setText("#"+player.jerseyNumber);

        //set player preferred getPosition
        TextView playerPosition = view.findViewById(R.id.player_position);
        if (player.preferredPosition != null)
            playerPosition.setText(player.preferredPosition.toString());
        else playerPosition.setVisibility(View.GONE);

        // set player name
        TextView playerName;
        playerName = view.findViewById(R.id.player_name);
        playerName.setText(player.name);

        // set player weight
        TextView playerWeight;
        playerWeight = view.findViewById(R.id.player_weight);
        playerWeight.setText(player.getWeightText());

        // set player height
        TextView playerHeight;
        playerHeight = view.findViewById(R.id.player_height);
        playerHeight.setText(player.getHeightText());

        return view;
    }
}
