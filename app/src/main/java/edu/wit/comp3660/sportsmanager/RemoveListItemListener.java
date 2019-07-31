package edu.wit.comp3660.sportsmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

public class RemoveListItemListener implements AdapterView.OnItemLongClickListener {

    private List data;
    private DialogCallback callback;

    public RemoveListItemListener(List list, DialogCallback callback) {
        this.data = list;
        this.callback = callback;
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        new AlertDialog.Builder(parent.getContext()).setPositiveButton("Remove",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callback.onRemoved(data.remove(position));
                    }
                })
                .setNegativeButton("Cancel", null)
                .setTitle("Are you sure you want to remove "+data.get(position)+" permanently?")
                .create()
                .show();
        return true;
    }
}
