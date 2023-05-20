package com.example.dca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class angus_beef extends AppCompatActivity {

    List<FoodListElement> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angus_beef);

        init();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void init() {
        elements = new ArrayList<>();
        Drawable frankpic = ContextCompat.getDrawable(this, R.drawable.allday);
        elements.add(new FoodListElement(frankpic, "Garden Franks", "Buttered Bun, Grilled Franks, Lettuce, Grilled Onion, Grilled Bell-pepper, Black Olives, Cheese Sauce & Fries ", "255"));
        elements.add(new FoodListElement(frankpic, "All Meat Franks", "Buttered Bun, Grilled Franks, Chili Con Carne, Pickles, Cheese Sauce, Sour Cream, Honey Mustard & Fries", "275"));


        FoodListAdapter listAdapter = new FoodListAdapter(elements, this);
        RecyclerView recyclerView = findViewById(R.id.angusRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }
}