package com.rahafmaria.naycelebrities.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.rahafmaria.naycelebrities.Model.DiorModel;
import com.rahafmaria.naycelebrities.PathUrls;
import com.rahafmaria.naycelebrities.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DiorAdapter extends RecyclerView.Adapter<DiorAdapter.DiorViewHolder> {
    ArrayList<DiorModel> diorModels;
    Context context = HomeActivity.context;


    public DiorAdapter(ArrayList<DiorModel> diorModels) {
        this.diorModels = diorModels;
    }

    @NonNull
    @Override
    public DiorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dior_view, parent, false);
        DiorViewHolder diorViewHolder = new DiorViewHolder(view);
        return diorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiorViewHolder holder, final int position) {
        String photo = PathUrls.baseUrl + "Images/" + diorModels.get(position).product_image;
        Picasso.get().load(photo).into(holder.product_image);
        holder.product_price.setText(diorModels.get(position).product_price + "");
        holder.product_name.setText(diorModels.get(position).product_name);
        holder.add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return diorModels.size();
    }

    public class DiorViewHolder extends RecyclerView.ViewHolder {
        TextView product_name, product_price;
        ImageView product_image, add_image;


        public DiorViewHolder(@NonNull View v) {
            super(v);
            product_image = v.findViewById(R.id.product_image);
            product_name = v.findViewById(R.id.product_name);
            product_price = v.findViewById(R.id.product_price);
            add_image = v.findViewById(R.id.add_button);


        }

    }

}
