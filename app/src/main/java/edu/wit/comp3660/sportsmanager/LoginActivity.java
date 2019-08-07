package edu.wit.comp3660.sportsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;
//import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private ProgressBar loadingBar;
    private String TAG = "loginActivity";
    final private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    EditText usernameBox;
    EditText passwordBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // get username and password
        usernameBox = findViewById(R.id.loginUsername);
        passwordBox = findViewById(R.id.loginPassword);

        // set onClick for login button
        Button login_button = findViewById(R.id.login_button);
        loadingBar = findViewById(R.id.progressBar);
        login_button.setOnClickListener(new LoginListener());

        // set onClick for register button
        Button registerButton = findViewById(R.id.registerBtn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void notifyDataLoaded(boolean didLoad) {
        if (didLoad) {
            Intent intent = new Intent(LoginActivity.this, TeamSelectActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Username/password does not match or exist", Toast.LENGTH_LONG)
                    .show();
        }
        loadingBar.setVisibility(View.INVISIBLE);
    }

    //TODO get all the data from firebase based on username/password

    /**
     * Load the data from Firebase into memory (LoadedData class)
     *
     * @return true if username and password exist/match
     */
    private boolean loadDataFromFirebase(String username, String password) {
        boolean useFirebase = true; //switch to true if you want to test Firebase
        if (useFirebase) {
            loadingBar.setVisibility(View.VISIBLE);
            LoadedData.get().fetchDataFromFirebase(username, this);
        } else {
            if (LoadedData.get().getTeams().isEmpty())
                LoadedData.get().createTeam("Leopards");
            notifyDataLoaded(true);
        }
        return true;
    }

    class LoginListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final String username = usernameBox.getText().toString();
            /*final String password = passwordBox.getText().toString();
            loadDataFromFirebase(username, password);

            if (username.equals("") || password.equals("")) {
                Toast.makeText(LoginActivity.this, "Must enter a username and password!",
                        Toast.LENGTH_SHORT).show();
                //notifyDataLoaded(true);
            } else {
                mAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    Toast.makeText(LoginActivity.this, "Authentication successful!",
                                            Toast.LENGTH_SHORT).show();
                                    //FirebaseUser user = mAuth.getCurrentUser();

                                    // store user/data and start next activity
                                    LoadedData.get().loggedInUser = username;
                                    notifyDataLoaded(true);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    usernameBox.getText().clear();
                                    usernameBox.setHint(R.string.username_hint);
                                    passwordBox.getText().clear();
                                    passwordBox.setHint(R.string.password_hint);
                                }
                            }
                        });
                    */
            if (loadDataFromFirebase(username, null)) {
                //LoadedData.get().loggedInUser = username;
                //wait for data to load
            } else {
                Toast.makeText(getApplicationContext(), "Username/password does not match or exit", Toast.LENGTH_LONG)
                        .show();
            }

        }
    }

}