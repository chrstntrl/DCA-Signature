package com.example.dca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.ContextCompat;

public class rice_meals extends AppCompatActivity {

    List<FoodListElement> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice_meals);

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
        Drawable katsu = ContextCompat.getDrawable(this, R.drawable.sanwich);
        elements.add(new FoodListElement(katsu, "Chicken Katsudon", "Deep Fried Breaded Chicken Fillet, Garden Coleslaw, Java Rice & Mushroom Gravy", "265"));
        elements.add(new FoodListElement(katsu, "Salisbury Steak","2-pc Grilled Salisbury Patties, Grilled Corn Cob, Sunny-Side Up Egg, Java Rice & Mushroom Gravy", "295"));


        FoodListAdapter listAdapter = new FoodListAdapter(elements, this);
        RecyclerView recyclerView = findViewById(R.id.riceRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }
}