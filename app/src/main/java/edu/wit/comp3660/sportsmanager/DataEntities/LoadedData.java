package edu.wit.comp3660.sportsmanager.DataEntities;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import edu.wit.comp3660.sportsmanager.LoginActivity;

public class LoadedData {

    private static LoadedData instance;

    public static LoadedData get() {
        if (instance == null)
            instance = new LoadedData();
        return instance;
    }

    private String TAG = "LoadedData";
    private ArrayList<Team> teams = new ArrayList<>();
    private int currentTeamIndex;
    private int currentGameIndex;
    public String loggedInUser;
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

    // TODO: call this when we add data, or when the app closes?
    public void syncAllDataToFirebase() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new user with a first and last name
        FirebaseTeam team = new FirebaseTeam(loggedInUser, teams);
        //user.put("password", "admin");

        // Add a new document with a generated ID
        db.collection("teams")
                .add(team)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void fetchDataFromFirebase(final String username, final LoginActivity callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("teams")
                .whereEqualTo("username", username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                FirebaseTeam data = document.toObject(FirebaseTeam.class);
                                teams = data.teams;
                                Log.d(TAG, "Loaded team 1 = " + teams.get(0).getName());

                                callback.notifyDataLoaded(true);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            callback.notifyDataLoaded(false);
                        }
                    }
                });
    }

    static class FirebaseTeam {
        String username;
        ArrayList<Team> teams;

        FirebaseTeam() {
            // we need this empty constructor, otherwise Firebase will error out
            username = "error";
            teams = null;
        }

        FirebaseTeam(String id, ArrayList<Team> data) {
            username = id;
            teams = data;
        }
    }
}
