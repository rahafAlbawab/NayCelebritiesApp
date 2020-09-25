package com.rahafmaria.naycelebrities.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahafmaria.naycelebrities.Activities.CelebritiesChatActivity;
import com.rahafmaria.naycelebrities.Model.CelebritiesChatListModel;
import com.rahafmaria.naycelebrities.PathUrls;
import com.rahafmaria.naycelebrities.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CelebritiesChatListAdapter extends RecyclerView.Adapter<CelebritiesChatListAdapter.CelebritiesChatListViewHolder> {
    ArrayList<CelebritiesChatListModel> celebritiesChatListModels;
    Context context;

    public CelebritiesChatListAdapter(ArrayList<CelebritiesChatListModel> celebritiesChatListModels, Context context) {
        this.celebritiesChatListModels = celebritiesChatListModels;
        this.context = context;
    }

    @NonNull
    @Override
    public CelebritiesChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celebrities_chat_list_view, parent, false);
        CelebritiesChatListViewHolder celebritiesChatListViewHolder = new CelebritiesChatListViewHolder(view);
        return celebritiesChatListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CelebritiesChatListViewHolder holder, final int position) {
        String photo = PathUrls.baseUrl + "UserImage/" + celebritiesChatListModels.get(position).user_image;
        Picasso.get().load(photo).into(holder.user_image);
        holder.user_name.setText(celebritiesChatListModels.get(position).user_name);
        holder.latest_message.setText(celebritiesChatListModels.get(position).latest_message);
        holder.go_to_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int user_id = celebritiesChatListModels.get(position).user_id;
                final String user_name= celebritiesChatListModels.get(position).user_name;
                final String user_image = celebritiesChatListModels.get(position).user_image;
                Intent intent = new Intent(context, CelebritiesChatActivity.class);
                intent.putExtra("user_id",user_id);
                intent.putExtra("user_name",user_name);
                intent.putExtra("user_image",user_image);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return celebritiesChatListModels.size();
    }

    public class CelebritiesChatListViewHolder extends RecyclerView.ViewHolder {
        ImageView user_image;
        TextView user_name;
        TextView latest_message;
        LinearLayout go_to_chat;

        public CelebritiesChatListViewHolder(@NonNull View itemView) {
            super(itemView);
            user_image = itemView.findViewById(R.id.user_image);
            user_name = itemView.findViewById(R.id.user_name);
            latest_message = itemView.findViewById(R.id.latest_message);
            go_to_chat = itemView.findViewById(R.id.go_to_chat);
        }
    }
}
