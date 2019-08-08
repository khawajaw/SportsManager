package edu.wit.comp3660.sportsmanager.DataEntities;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import edu.wit.comp3660.sportsmanager.LoginActivity;

public class LoadedData {

    private static LoadedData instance;

    public static LoadedData get() {
        if (instance == null)
            instance = new LoadedData();
        return instance;
    }

    public static void reset() {
        instance = null;
    }

    private String TAG = "LoadedData";
    private ArrayList<Team> teams = new ArrayList<>();
    private int currentTeamIndex;
    private int currentGameIndex;
    public String loggedInUser;
    public int user_ID_COUNTER;
    public final Sport[] SPORTS = {
            new Sport("Football"),
            new Sport("Soccer"),
            new Sport("Baseball"),
            new Sport("Basketball"),
    };

    public static void changeCurrentTeamIndex(int position) {
        instance.currentTeamIndex = position;
    }

    public static void updateCurrentGameIndex(int i) {
        instance.currentGameIndex = i;
    }

    public void createTeam(String name) {
        createTeam(name, SPORTS[3]); //just create a basketball team
    }

    public void createTeam(String name, Sport sport) {
        teams.add(new Team(name, sport));
    }

    public void createGame(String opponentName, String gameLocation, String gameDate, String gameTime, String option) {
        getCurrentTeam().addGame(new Game(opponentName, gameLocation, gameDate, gameTime, option));
    }

    public Team getCurrentTeam() {
        return teams.get(currentTeamIndex);
    }

    public int getCurrentGameIndex() {
        return currentGameIndex;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     * This should run in the background and not slow the UI
     */
    public void syncAllDataToFirebase() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        FirebaseTeam team = new FirebaseTeam(user_ID_COUNTER, teams);

        // Add a new document with a generated ID
        db.collection("teams").document(loggedInUser)
                .set(team)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + loggedInUser);
                    }
                });
    }

    public void fetchDataFromFirebase(final String username, final LoginActivity callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("teams")
                .document(username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        FirebaseTeam data = null;
                        if (document != null) data = document.toObject(FirebaseTeam.class);
                        if (task.isSuccessful() && data != null) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                            teams = data.teams;
                            user_ID_COUNTER = data.ID_counter;
                            loggedInUser = username;
                            Log.d(TAG, "Loaded team 1 = " + teams.get(0).getName());

                            callback.notifyDataLoaded(true);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            callback.notifyDataLoaded(false);
                        }
                    }
                });
    }


    static class FirebaseTeam {
        int ID_counter;
        ArrayList<Team> teams;

        FirebaseTeam() {
            // we need this empty constructor, otherwise Firebase will error out
            ID_counter = -1;
            teams = null;
        }

        FirebaseTeam(int id_count, ArrayList<Team> data) {
            ID_counter = id_count;
            teams = data;
        }
    }
}
