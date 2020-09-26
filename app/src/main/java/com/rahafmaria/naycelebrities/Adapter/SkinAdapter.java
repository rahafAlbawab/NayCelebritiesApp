package com.rahafmaria.naycelebrities.Adapter;


import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahafmaria.naycelebrities.Activities.HomeActivity;
import com.rahafmaria.naycelebrities.Database.RemoteDB;
import com.rahafmaria.naycelebrities.Model.SkinModel;
import com.rahafmaria.naycelebrities.PathUrls;
import com.rahafmaria.naycelebrities.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class SkinAdapter extends RecyclerView.Adapter<SkinAdapter.SkinViewHolder> {
    ArrayList<SkinModel> skinModel;
    Context context = HomeActivity.context;
    RemoteDB remoteDB = new RemoteDB(context);
    SharedPreferences sharedPreferences = context.getSharedPreferences("loginCheck", MODE_PRIVATE);

    public SkinAdapter(ArrayList<SkinModel> skinModel) {
        this.skinModel = skinModel;
    }

    @NonNull
    @Override
    public SkinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skin_view, parent, false);
        SkinViewHolder skinViewHolder = new SkinViewHolder(view);
        return skinViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SkinViewHolder holder, final int position) {
        String photo = PathUrls.baseUrl + "Images/" + skinModel.get(position).product_image;
        Picasso.get().load(photo).into(holder.product_image);
        holder.product_price.setText(skinModel.get(position).product_price + "");
        holder.product_name.setText(skinModel.get(position).product_name);
        holder.add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remoteDB.addFavouriteProduct(skinModel.get(position).product_id
                        , Integer.parseInt(sharedPreferences.getString("user_id", "")));
            }
        });
    }

    @Override
    public int getItemCount() {
        return skinModel.size();
    }


    public class SkinViewHolder extends RecyclerView.ViewHolder {
        TextView product_name, product_price;
        ImageView product_image, add_image;


        public SkinViewHolder(@NonNull View v) {
            super(v);
            product_image = v.findViewById(R.id.product_image);
            product_name = v.findViewById(R.id.product_name);
            product_price = v.findViewById(R.id.product_price);
            add_image = v.findViewById(R.id.add_button);


        }
    }
}