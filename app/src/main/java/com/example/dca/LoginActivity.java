package com.example.dca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText liusername, lipass;
    TextView lisignup;
    Button libutton;

    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        liusername = findViewById(R.id.li_username);
        lipass = findViewById(R.id.li_pass);
        lisignup = findViewById(R.id.li_signup);
        libutton = findViewById(R.id.li_button);


        libutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUsername() || !validatePassword()) {
                    return;
                }
                checkUser();
            }
        });

        lisignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validateUsername() {
        String val = liusername.getText().toString();
        if (val.isEmpty()) {
            liusername.setError("Please input your username.");
            return false;
        } else {
            liusername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = lipass.getText().toString();
        if (val.isEmpty()) {
            lipass.setError("Please input your password.");
            return false;
        } else {
            lipass.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String Uusername = liusername.getText().toString().trim();
        String Upassword = lipass.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(Uusername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean foundUser = false;
                boolean isAdmin = false;
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String username = userSnapshot.child("username").getValue(String.class);
                    if (username.equals(Uusername)) {
                        foundUser = true;
                        String passDB = userSnapshot.child("password").getValue(String.class);
                        String role = userSnapshot.child("role").getValue(String.class);

                        if (passDB.equals(Upassword)) {
                            liusername.setError(null);
                            if (role == null || role.isEmpty()) {
                                // Open MainActivity
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else if (role.equals("admin")) {
                                // Open AdminPanel activity
                                Intent intent = new Intent(LoginActivity.this, AdminPanel.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                // Handle other roles if needed
                            }
                        } else {
                            lipass.setError("Invalid Credentials");
                            lipass.requestFocus();
                        }
                        break;
                    }
                }
                if (!foundUser) {
                    liusername.setError("Username doesn't exist");
                    liusername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_USERNAME, liusername.getText().toString());
        outState.putString(KEY_PASSWORD, lipass.getText().toString());
    }
}
