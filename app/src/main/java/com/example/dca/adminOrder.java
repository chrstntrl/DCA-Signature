package com.example.dca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.icu.text.AlphabeticIndex;
import android.os.Bundle;

import com.example.dca.LoginActivity;
import com.example.dca.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class adminOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_products);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_orders:
                    return true;
                case R.id.bottom_products:
                    startActivity(new Intent(getApplicationContext(), AdminPanel.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_logout:
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                    return true;
            }
            return false;
        });
    }
}