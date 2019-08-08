package edu.wit.comp3660.sportsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import edu.wit.comp3660.sportsmanager.DataEntities.LoadedData;

public class RegisterActivity extends AppCompatActivity {

    private ProgressBar loadingBar;
    private String TAG = "registerActivity";
    final private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        // get username and password
        final EditText u = findViewById(R.id.registerUsername);
        final EditText p = findViewById(R.id.registerPassword);

        // set onClick for login button
        Button registerButton = findViewById(R.id.registerBtn);
        loadingBar = findViewById(R.id.progressBar);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = u.getText().toString();
                final String password = p.getText().toString();
                if(username.equals("") || password.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Must enter a username and/or password!",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(username, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        //FirebaseUser user = mAuth.getCurrentUser();
                                        LoadedData.get().loggedInUser = username;
                                        notifyDataLoaded();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(RegisterActivity.this,
                                                "Authentication failed: "+task.getException().getLocalizedMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }

                                    u.getText().clear();
                                    u.setHint(R.string.username_hint);
                                    p.getText().clear();
                                    p.setHint(R.string.password_hint);
                                }
                            });
                }
            }
        });

        // set onClick for register button
        Button loginButton = findViewById(R.id.loginBtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void notifyDataLoaded() {
        Intent intent = new Intent(RegisterActivity.this, TeamSelectActivity.class);
        startActivity(intent);
        loadingBar.setVisibility(View.INVISIBLE);
    }
}
