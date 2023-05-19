package com.example.dca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class food_sec extends AppCompatActivity {

    List<FoodListElement> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_sec);

        init();
    }

    public void init() {
        elements = new ArrayList<>();
        elements.add(new FoodListElement("Waffles, Bacon and Eggs", "Honey \\u0026 Butter Belgian Waffles, Smoked Bacon, Sunny-Side Up Eggs, Served with Whipped Cream", "225"));
        elements.add(new FoodListElement("Salad, Pasta and Wings", "Lettuce, Cucumber, Tomato with Thousand Island Dressing, Red Sauce Pasta \\u0026 1 piece Fried Chicken ", "235"));
        elements.add(new FoodListElement("Rice, Pasta and Wings", "Rice, Red Sauce Pasta & 1 piece Fried Chicken", "235"));

        FoodListAdapter listAdapter = new FoodListAdapter(elements, this);
        RecyclerView recyclerView = findViewById(R.id.foodsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }
}