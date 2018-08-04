package com.example.sagar.foodapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sagar.foodapp.Model.Category;
import com.example.sagar.foodapp.Model.Food;
import com.example.sagar.foodapp.adapters.FoodAdapter;
import com.example.sagar.foodapp.adapters.MenuAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FoodList extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference foodList;
    String categoryId="";
    RecyclerView recycler_food;
    FoodAdapter adapter;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        initView();
    }
    public void initView(){

        database=FirebaseDatabase.getInstance();
        foodList=database.getReference("Foods");
        recycler_food = (RecyclerView) findViewById(R.id.recycler_food);
        recycler_food.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_food.setLayoutManager(layoutManager);

        if(getIntent()!=null)
            categoryId=getIntent().getStringExtra("CategoryId");
        if(categoryId!=null)
            loadListFood(categoryId);

    }

    private void loadListFood(String categoryId) {


        final List<Food> myList = new ArrayList<>();
         Query  myQuery =  database.getReference("Foods").orderByChild("MenuId").equalTo(categoryId);


                
       myQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String a=dataSnapshot.getValue().toString();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Food food=postSnapshot.getValue(Food.class);
                    food.setId(postSnapshot.getKey());
                    myList.add(food);
                }
                adapter = new FoodAdapter(myList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recycler_food.setLayoutManager(mLayoutManager);
                recycler_food.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
