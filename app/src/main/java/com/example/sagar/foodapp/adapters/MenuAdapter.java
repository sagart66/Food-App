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
import com.example.sagar.foodapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private List<Category> List;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtMenuName;
        public ImageView imgMenuImage;
        public String key;

        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            txtMenuName = (TextView)itemView.findViewById(R.id.txtMenuItem);
            imgMenuImage = (ImageView)itemView.findViewById(R.id.imgMenuItem);
        }

        @Override
        public  void onClick(View view) {
                Intent intent  = new Intent(view.getContext(), FoodList.class);
                intent.putExtra("CategoryId",key);
                view.getContext().startActivity(intent);
        }
    }


    public MenuAdapter(List<Category> List) {
        this.List = List;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       Category category = List.get(position);
       holder.txtMenuName.setText(category.getName());
       holder.key=category.getId();
        Picasso.get().load(category.getImage()).into(holder.imgMenuImage);

    }




    @Override
    public int getItemCount() {
        return List.size();
    }
}