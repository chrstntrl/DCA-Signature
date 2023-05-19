package com.example.dca;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
@SuppressLint("CustomSplashScreen")
public class Splashscreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(Splashscreen.this, SignupActivity.class));
            finish();
        }, 3000);

    }

}
