package com.app.appcafedemo.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.appcafedemo.Activity.ListDrinksActivity;
import com.app.appcafedemo.Domain.Category;
import com.app.appcafedemo.Domain.Drinks;
import com.app.appcafedemo.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder> {
    ArrayList<Category> items;
    Context context;
    public CategoryAdapter(ArrayList<Category> items){
        this.items = items;
    }
    @NonNull
    @Override
    public CategoryAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new viewholder(inflate);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewholder holder, int position) {
        holder.catNameTxt.setText(items.get(position).getName());
        switch (position){
            case 0:{
                holder.imageCat.setBackgroundResource(R.drawable.cat_1_background);
                break;
            }
            case 1:{
                holder.imageCat.setImageResource(R.drawable.cat_2_background);
                break;
            }
            case 2:{
                holder.imageCat.setBackgroundResource(R.drawable.cat_3_background);
                break;
            }
            case 3:{
                holder.imageCat.setBackgroundResource(R.drawable.cat_4_background);
                break;
            }
            case 4:{
                holder.imageCat.setBackgroundResource(R.drawable.cat_5_background);
                break;
            }
            case 5:{
                holder.imageCat.setBackgroundResource(R.drawable.cat_6_background);
                break;
            }
            case 6:{
                holder.imageCat.setBackgroundResource(R.drawable.cat_7_background);
            }
            case 7:{
                holder.imageCat.setBackgroundResource(R.drawable.cat_8_background);
                break;
            }
        }
        int drawableResourceId = context.getResources().getIdentifier(items.get(position).getImagePath(),
                "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(context)
                .load(items.get(position).getImagePath())
                .into(holder.imageCat);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ListDrinksActivity.class);
                intent.putExtra("CategoryId",items.get(position).getId());
                intent .putExtra("CategoryName",items.get(position).getName());
                context.startActivity(intent);
            }
        });
    };

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView catNameTxt;
        ImageView imageCat;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            catNameTxt = itemView.findViewById(R.id.catNameTxt);
            imageCat = itemView.findViewById(R.id.imageCat);

        }
    }
}
