package com.example.dca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText suname, suemail, suusername, supass;
    TextView sulogin;
    Button subutton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        suname = findViewById(R.id.su_name);
        suemail = findViewById(R.id.su_email);
        suusername = findViewById(R.id.su_username);
        supass = findViewById(R.id.su_pass);
        sulogin = findViewById(R.id.su_login);
        subutton = findViewById(R.id.su_button);

        subutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = suname.getText().toString();
                String email = suemail.getText().toString();
                String username = suusername.getText().toString();
                String password = supass.getText().toString();

                // TODO: Validate user input here
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(SignupActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                HelperClass helperClass = new HelperClass(name, email, username, password);

                reference.child(username).setValue(helperClass)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(SignupActivity.this, "Sign up successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignupActivity.this, "Sign up failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        sulogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
