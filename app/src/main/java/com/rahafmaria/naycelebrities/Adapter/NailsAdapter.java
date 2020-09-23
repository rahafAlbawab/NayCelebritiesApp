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
import com.rahafmaria.naycelebrities.Model.NailsModel;
import com.rahafmaria.naycelebrities.PathUrls;
import com.rahafmaria.naycelebrities.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class NailsAdapter extends RecyclerView.Adapter<NailsAdapter.NailsViewHolder> {
    ArrayList<NailsModel> nailsModel;
    Context context = HomeActivity.context;
    RemoteDB remoteDB = new RemoteDB(context);
    SharedPreferences sharedPreferences = context.getSharedPreferences("loginCheck", MODE_PRIVATE);


    public NailsAdapter(ArrayList<NailsModel> nailsModel) {
        this.nailsModel = nailsModel;
    }

    @NonNull
    @Override
    public NailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.face_view, parent, false);
        NailsViewHolder nailsViewHolder = new NailsViewHolder(view);
        return nailsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NailsViewHolder holder, final int position) {
        String photo = PathUrls.baseUrl + "Images/" + nailsModel.get(position).product_image;
        Picasso.get().load(photo).into(holder.product_image);
        holder.product_price.setText(nailsModel.get(position).product_price + "");
        holder.product_name.setText(nailsModel.get(position).product_name);
        holder.add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remoteDB.addFavouriteProduct(nailsModel.get(position).product_id
                        ,Integer.parseInt(sharedPreferences.getString("user_id","")));
            }
        });

    }

    @Override
    public int getItemCount() {
        return nailsModel.size();
    }

    public class NailsViewHolder extends RecyclerView.ViewHolder {
        TextView product_name, product_price;
        ImageView product_image, add_image;


        public NailsViewHolder(@NonNull View v) {
            super(v);
            product_image = v.findViewById(R.id.product_image);
            product_name = v.findViewById(R.id.product_name);
            product_price = v.findViewById(R.id.product_price);
            add_image = v.findViewById(R.id.add_button);


        }

    }
}
