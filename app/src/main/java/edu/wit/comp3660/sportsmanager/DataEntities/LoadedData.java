package edu.wit.comp3660.sportsmanager.DataEntities;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import edu.wit.comp3660.sportsmanager.LoginActivity;

public class LoadedData {

    private static LoadedData instance;

    public static LoadedData get() {
        if (instance == null)
            instance = new LoadedData();
        return instance;
    }

    private final FirebaseFirestore fireDB;
    private final FirebaseStorage storage;
    // Create a storage reference from our app
    private StorageReference userRef;

    LoadedData() {
        fireDB = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
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

    public void logIn(String username) {
        loggedInUser = username;
        userRef = storage.getReferenceFromUrl("gs://sportsmanager-wajowe.appspot.com/")
                .child(loggedInUser);
    }

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
        FirebaseTeam team = new FirebaseTeam(user_ID_COUNTER, teams);
        if (loggedInUser == null) return;
        // Add a new document with a generated ID
        fireDB.collection("teams").document(loggedInUser)
                .set(team)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot updated with ID: " + loggedInUser);
                    }
                });
    }

    public void fetchDataFromFirebase(final String username, final LoginActivity callback) {
        if (username == null) {
            callback.notifyDataLoaded(false);
            return;
        }
        fireDB.collection("teams")
                .document(username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        FirebaseTeam data = null;
                        if (document != null) data = document.toObject(FirebaseTeam.class);
                        if (task.isSuccessful()) {
                            if (data != null) {//account for case of accounts with no data
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                teams = data.teams;
                                user_ID_COUNTER = data.ID_counter;
                            }
                            logIn(username);
                            callback.notifyDataLoaded(true);
                            fetchPhotosFromFirestore();
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            reset();
                            callback.notifyDataLoaded(false);
                        }
                    }
                });
    }

    private final long ONE_MEGABYTE = 1024 * 1024;
    private void fetchPhotosFromFirestore() {
        //at this point, all team and player data should be loaded, just need their images
        //Load team images first, since that's what the user will see first
        for (final Team e: teams) {
            if (e.logoIsSet)
                Log.v("myApp", "trying to open logos/"+e.ID+".jpg");
                userRef.child("logos/"+e.ID+".jpg").getBytes(ONE_MEGABYTE)
                    .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            e.loadLogo(bytes);
                        }
                    });
        }
        for (final Team team: teams) {
            for (final Player e: team.getRoster()) {
                if (e.avatarIsSet)
                    userRef.child("avatars/"+e.ID+".jpg").getBytes(ONE_MEGABYTE)
                        .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                e.changePlayerImage(bytes);
                            }
                        });
            }
        }

    }

    public byte[] generateBitmapBytes(Bitmap image) {
        if (image == null) return null;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 50, byteStream);

        return byteStream.toByteArray();
        //  store & retrieve this string to firebase
    }

    void uploadImageToFirestore(String localPath, Bitmap image) {
        StorageReference imageRef = userRef.child(localPath+".jpg");

        UploadTask uploadTask = imageRef.putBytes(generateBitmapBytes(image));
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                //Uri downloadUrl = taskSnapshot.getDownloadUrl();
            }
        });
    }


    @Keep
    static class FirebaseTeam {
        int ID_counter;
        ArrayList<Team> teams;

        @Keep
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
