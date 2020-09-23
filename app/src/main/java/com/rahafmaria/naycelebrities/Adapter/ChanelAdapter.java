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
import com.rahafmaria.naycelebrities.Model.ChanelModel;
import com.rahafmaria.naycelebrities.PathUrls;
import com.rahafmaria.naycelebrities.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChanelAdapter extends RecyclerView.Adapter<ChanelAdapter.ChanelViewHolder> {
    ArrayList<ChanelModel> chanelModel;
    Context context = HomeActivity.context;


    public ChanelAdapter(ArrayList<ChanelModel> chanelModel) {
        this.chanelModel = chanelModel;
    }

    @NonNull
    @Override
    public ChanelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chanel_view, parent, false);
        ChanelViewHolder chanelViewHolder = new ChanelViewHolder(view);
        return chanelViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChanelViewHolder holder, final int position) {
        String photo = PathUrls.baseUrl + "Images/" + chanelModel.get(position).product_image;
        Picasso.get().load(photo).into(holder.product_image);
        holder.product_price.setText(chanelModel.get(position).product_price + "");
        holder.product_name.setText(chanelModel.get(position).product_name);
        holder.add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return chanelModel.size();
    }

    public class ChanelViewHolder extends RecyclerView.ViewHolder {
        TextView product_name, product_price;
        ImageView product_image, add_image;


        public ChanelViewHolder(@NonNull View v) {
            super(v);
            product_image = v.findViewById(R.id.product_image);
            product_name = v.findViewById(R.id.product_name);
            product_price = v.findViewById(R.id.product_price);
            add_image = v.findViewById(R.id.add_button);


        }

    }
}
