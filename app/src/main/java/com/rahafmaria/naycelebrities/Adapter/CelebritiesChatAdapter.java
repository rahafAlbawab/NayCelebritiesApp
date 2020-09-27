package com.rahafmaria.naycelebrities.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahafmaria.naycelebrities.Model.CelebritiesChatModel;
import com.rahafmaria.naycelebrities.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CelebritiesChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<CelebritiesChatModel> celebritiesChatModels;
    Context context;
    int user_id;

    public CelebritiesChatAdapter(ArrayList<CelebritiesChatModel> celebritiesChatModels, Context context, int user_id) {
        this.celebritiesChatModels = celebritiesChatModels;
        this.context = context;
        this.user_id = user_id;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.send_text_view, parent, false);
            TextSendHolder textSendHolder = new TextSendHolder(view);
            return textSendHolder;
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.send_image_view, parent, false);
            ImageSendHolder imageSendHolder = new ImageSendHolder(view);
            return imageSendHolder;
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receive_text_view, parent, false);
            TextReceiveHolder textReceiveHolder = new TextReceiveHolder(view);
            return textReceiveHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receive_image_view, parent, false);
            ImageReceiveHolder imageReceiveHolder = new ImageReceiveHolder(view);
            return imageReceiveHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (celebritiesChatModels.get(position).user_id == user_id) {
            if (celebritiesChatModels.get(position).type == 1) {
                ((TextSendHolder) holder).text_sent.setText(celebritiesChatModels.get(position).message);
            } else {

                Picasso.get().load(celebritiesChatModels.get(position).message).into(((ImageSendHolder) holder).image_sent);
            }
        } else {
            if (celebritiesChatModels.get(position).type == 1) {
                ((TextReceiveHolder) holder).text_receive.setText(celebritiesChatModels.get(position).message);
            } else {

                Picasso.get().load(celebritiesChatModels.get(position).message).into(((ImageReceiveHolder) holder).image_receive);
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (celebritiesChatModels.get(position).user_id == user_id) {
            if (celebritiesChatModels.get(position).type == 1) {
                return 1;
            } else {
                return 2;
            }
        } else {
            if (celebritiesChatModels.get(position).type == 1) {
                return 3;
            } else {
                return 4;
            }
        }
    }

    @Override
    public int getItemCount() {
        return celebritiesChatModels.size();
    }


    public class TextSendHolder extends RecyclerView.ViewHolder {

        TextView text_sent;


        public TextSendHolder(@NonNull View itemView) {
            super(itemView);

            text_sent = itemView.findViewById(R.id.text_sent);

        }
    }

    public class TextReceiveHolder extends RecyclerView.ViewHolder {

        TextView text_receive;

        public TextReceiveHolder(@NonNull View itemView) {
            super(itemView);

            text_receive = itemView.findViewById(R.id.text_receive);
        }
    }

    public class ImageSendHolder extends RecyclerView.ViewHolder {

        ImageView image_sent;


        public ImageSendHolder(@NonNull View itemView) {
            super(itemView);

            image_sent = itemView.findViewById(R.id.image_sent);

        }
    }

    public class ImageReceiveHolder extends RecyclerView.ViewHolder {
        ImageView image_receive;


        public ImageReceiveHolder(@NonNull View itemView) {
            super(itemView);
            image_receive = itemView.findViewById(R.id.image_receive);

        }
    }
}
