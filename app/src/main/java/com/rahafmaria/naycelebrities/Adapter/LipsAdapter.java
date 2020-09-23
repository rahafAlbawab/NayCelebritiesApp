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
import com.rahafmaria.naycelebrities.Model.LipsModel;
import com.rahafmaria.naycelebrities.PathUrls;
import com.rahafmaria.naycelebrities.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LipsAdapter extends RecyclerView.Adapter<LipsAdapter.LipsViewHolder> {
    ArrayList<LipsModel> lipsModels;
    Context context = HomeActivity.context;


    public LipsAdapter(ArrayList<LipsModel> lipsModels) {
        this.lipsModels = lipsModels;
    }

    @NonNull
    @Override
    public LipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lips_view, parent, false);
        LipsViewHolder lipsViewHolder = new LipsViewHolder(view);
        return lipsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LipsViewHolder holder, final int position) {
        String photo = PathUrls.baseUrl + "Images/" + lipsModels.get(position).product_image;
        Picasso.get().load(photo).into(holder.product_image);
        holder.product_price.setText(lipsModels.get(position).product_price + "");
        holder.product_name.setText(lipsModels.get(position).product_name);
        holder.add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return lipsModels.size();
    }

    public class LipsViewHolder extends RecyclerView.ViewHolder {
        TextView product_name, product_price;
        ImageView product_image, add_image;


        public LipsViewHolder(@NonNull View v) {
            super(v);
            product_image = v.findViewById(R.id.product_image);
            product_name = v.findViewById(R.id.product_name);
            product_price = v.findViewById(R.id.product_price);
            add_image = v.findViewById(R.id.add_button);

        }

    }
}
