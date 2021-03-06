package com.example.sagar.foodapp.adapters;


import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sagar.foodapp.FoodList;
import com.example.sagar.foodapp.Model.Category;
import com.example.sagar.foodapp.Model.Food;
import com.example.sagar.foodapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {

    private List<Food> List;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtFoodName;
        public ImageView imgFoodImage;

        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            txtFoodName = (TextView)itemView.findViewById(R.id.txtFoodItem);
            imgFoodImage = (ImageView)itemView.findViewById(R.id.imgFoodItem);
        }

        @Override
        public  void onClick(View view) {


            Toast.makeText(view.getContext(),""+getAdapterPosition(),Toast.LENGTH_SHORT).show();
        }
    }


    public FoodAdapter(java.util.List<Food> List) {
        this.List = List;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Food food = List.get(position);
        holder.txtFoodName.setText(food.getName());
        Picasso.get().load(food.getImage()).into(holder.imgFoodImage);

    }




    @Override
    public int getItemCount() {
        return List.size();
    }
}