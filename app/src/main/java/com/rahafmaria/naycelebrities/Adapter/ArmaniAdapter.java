package com.rahafmaria.naycelebrities.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahafmaria.naycelebrities.Activities.HomeActivity;
import com.rahafmaria.naycelebrities.Model.ArmaniModel;
import com.rahafmaria.naycelebrities.PathUrls;
import com.rahafmaria.naycelebrities.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArmaniAdapter extends RecyclerView.Adapter<ArmaniAdapter.ArmaniViewHolder> {
    ArrayList<ArmaniModel> armaniModel;
    Context context = HomeActivity.context;

    public ArmaniAdapter(ArrayList<ArmaniModel> armaniModel) {
        this.armaniModel = armaniModel;
    }

    @NonNull
    @Override
    public ArmaniViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.armani_view, parent, false);
        ArmaniViewHolder armaniViewHolder = new ArmaniViewHolder(view);
        return armaniViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ArmaniViewHolder holder, final int position) {
        // 1- To get image from server by link :https://beautycosmeticsapp.000webhostapp.com/Images/armani.jpg
        String photo = PathUrls.baseUrl+ "Images/"+armaniModel.get(position).product_image;
        //2- to Display image come from internet should use picasso
        //photo -> link of image
        //holder.product_image -> The location where the image is displayed
        Picasso.get().load(photo).into(holder.product_image);
        holder.product_price.setText(armaniModel.get(position).product_price+"");
        holder.product_name.setText(armaniModel.get(position).product_name);
        holder.add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return armaniModel.size();
    }

    public class ArmaniViewHolder extends RecyclerView.ViewHolder {
        TextView product_name, product_price;
        ImageView product_image , add_image;


        public ArmaniViewHolder(@NonNull View v) {
            super(v);
            product_image = v.findViewById(R.id.product_image);
            product_name = v.findViewById(R.id.product_name);
            product_price = v.findViewById(R.id.product_price);
            add_image = v.findViewById(R.id.add_button);


        }

    }

}
