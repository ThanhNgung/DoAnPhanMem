package com.app.appcafedemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.appcafedemo.Domain.Drinks;
import com.app.appcafedemo.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.android.play.core.integrity.p;

import java.util.ArrayList;

public class DrinkListAdapter extends RecyclerView.Adapter<DrinkListAdapter.viewholder> {
    ArrayList<Drinks> items;
    Context context;
    public DrinkListAdapter(ArrayList<Drinks> items){
        this.items = items;
    }
    @NonNull
    @Override
    public DrinkListAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.viewholder_list_drink,parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkListAdapter.viewholder holder, int position) {
        holder.timeTxt.setText(items.get(position).getTitle());
        holder.priceTxt.setText("đ"+items.get(position).getPrice());
        holder.rateTxt.setText(""+items.get(position).getStar());
        holder.timeTxt.setText(items.get(position).getTimeValue()+"min");

        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(),new RoundedCorners(30))
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView titleTxt,priceTxt,rateTxt,timeTxt;
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            rateTxt = itemView.findViewById(R.id.rateTxt);
            timeTxt = itemView.findViewById(R.id.timeTxt);
            pic = itemView.findViewById(R.id.img);
        }
    }
}
