package edu.wit.comp3660.sportsmanager;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.wit.comp3660.sportsmanager.ListAdapters.LineupAdapter;
import edu.wit.comp3660.sportsmanager.ListAdapters.LineupObject;

public class LineupFragment extends Fragment {

    private final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private LineupAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lineup, container, false);

        recyclerView = rootView.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        List<LineupObject> posts = returnListItems();

        adapter = new LineupAdapter(getActivity(), posts);
        recyclerView.setAdapter(adapter);

        recyclerView.setAdapter(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(rvCallback);
        touchHelper.attachToRecyclerView(recyclerView);

        setHasOptionsMenu(true);
        return rootView;


    }
    int dragStartFromPosition = -1;
    ItemTouchHelper.Callback rvCallback = new ItemTouchHelper.Callback() {

        int dragFromPosition = -1;
        int dragToPosition = -1;

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            //final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            return makeMovementFlags(dragFlags, 0);
        }


        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

            if (dragFromPosition == -1) { //For Saving drag start from position
                dragStartFromPosition = viewHolder.getAdapterPosition();
            }

            dragFromPosition = viewHolder.getAdapterPosition();
            dragToPosition = target.getAdapterPosition();
            moveItem(dragFromPosition, dragToPosition);
            dragFromPosition = dragToPosition;
            dragToPosition = -1;
            return true;
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);

            if (dragFromPosition != -1 && dragToPosition != -1 && dragFromPosition != dragToPosition) {
                finalMoved(dragFromPosition, dragToPosition);
            } else {
                moveItem(dragFromPosition, dragStartFromPosition);
                dragStartFromPosition = -1;
            }
            dragFromPosition = dragToPosition = -1;
        }

        private void finalMoved(int from, int to) {
            //moveAlertDialog(from, to);
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        }

        @Override
        public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }

    };

    private void moveItem(int from, int to) {
        Collections.swap(returnListItems(), from, to);
        adapter.notifyItemMoved(from, to);
    }

//    public void moveAlertDialog(final int from, final int to) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setPositiveButton("move", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dragStartFromPosition = -1;
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                moveItem(from, dragStartFromPosition);
//                dragStartFromPosition = -1;
//            }
//        });
//        builder.show();
//    }


    private List<LineupObject> returnListItems(){
        List<LineupObject> items = new ArrayList<LineupObject>();
        items.add(new LineupObject("Wajih Khawaja", "ST", "1"));
        items.add(new LineupObject("Wes Brimeyer", "ST", "2"));
        items.add(new LineupObject("Jose Fossi", "ST", "3"));
        return items;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        menu.removeItem(R.id.add_menu_action);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.v(TAG, "Id: "+item.getItemId());
        switch (item.getItemId()) {
            case R.id.save_menu_action:
                break;
            case R.id.edit_menu_action:
                Toast.makeText(getContext(), "Select first player to swap", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}