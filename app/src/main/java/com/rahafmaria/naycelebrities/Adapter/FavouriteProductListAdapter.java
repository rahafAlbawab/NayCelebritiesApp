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

import com.rahafmaria.naycelebrities.Activities.FavouriteProductListActivity;
import com.rahafmaria.naycelebrities.Database.RemoteDB;
import com.rahafmaria.naycelebrities.Model.FavouriteProductListModel;
import com.rahafmaria.naycelebrities.PathUrls;
import com.rahafmaria.naycelebrities.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class FavouriteProductListAdapter extends RecyclerView.Adapter<FavouriteProductListAdapter.FavouriteProductListViewHolder> {
    Context context = FavouriteProductListActivity.context;
    ArrayList<FavouriteProductListModel> favouriteProductListModels;
    RemoteDB remoteDB = new RemoteDB(context);
    SharedPreferences sharedPreferences = context.getSharedPreferences("loginCheck", MODE_PRIVATE);

    public FavouriteProductListAdapter(ArrayList<FavouriteProductListModel> favouriteProductListModels) {

        this.favouriteProductListModels = favouriteProductListModels;
    }

    @NonNull
    @Override
    public FavouriteProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_product_list_view, parent, false);
        FavouriteProductListViewHolder favouriteProductListViewHolder = new FavouriteProductListViewHolder(view);
        return favouriteProductListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteProductListViewHolder holder,final int position) {

        String photo = PathUrls.baseUrl + "Images/" + favouriteProductListModels.get(position).product_image;
        Picasso.get().load(photo).into(holder.product_image);
        holder.product_name.setText(favouriteProductListModels.get(position).product_name);
        holder.product_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remoteDB.removeProductFromFavouriteList(favouriteProductListModels.get(position).product_id
                        ,Integer.parseInt(sharedPreferences.getString("user_id","")));
                favouriteProductListModels.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return favouriteProductListModels.size();
    }

    public class FavouriteProductListViewHolder extends RecyclerView.ViewHolder {
        ImageView product_image;
        ImageView product_delete;
        TextView product_name;


        public FavouriteProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.product_image);
            product_delete = itemView.findViewById(R.id.product_delete);
            product_name = itemView.findViewById(R.id.product_name);

        }
    }
}
