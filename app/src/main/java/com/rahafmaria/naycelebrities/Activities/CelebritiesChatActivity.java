package com.rahafmaria.naycelebrities.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.rahafmaria.naycelebrities.R;
import com.squareup.picasso.Picasso;

public class CelebritiesChatActivity extends AppCompatActivity {
    ImageView camera_chat;
    ImageView send_chat;
    ImageView arrow_icon;
    ImageView user_image;
    TextView user_name;
    EditText text_chat;
    RecyclerView recycler_chat;
    Intent getUserInfo;
    String userName;
    String userImage;
    public static int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrities_chat);
        Initialization();
        listeners();
        if (getUserInfo.getStringExtra("user_name") != null
                && getUserInfo.getStringExtra("user_image") != null
                && getUserInfo.getIntExtra("user_id", 0) != 0) {
            Log.d("getUserInfo",getUserInfo.getStringExtra("user_image"));
            Log.d("getUserInfo",getUserInfo.getStringExtra("user_name"));
            userName = getUserInfo.getStringExtra("user_name");
            userImage = getUserInfo.getStringExtra("user_image");
            user_id = getUserInfo.getIntExtra("user_id", 0);
            user_name.setText(userName);
            Picasso.get().load(userImage).into(user_image);


        }
    }

    private void Initialization() {
        camera_chat = findViewById(R.id.camera_chat);
        arrow_icon = findViewById(R.id.arrow_icon);
        user_image = findViewById(R.id.user_image);
        user_name = findViewById(R.id.user_name);
        send_chat = findViewById(R.id.send_chat);
        text_chat = findViewById(R.id.text_chat);
        recycler_chat = findViewById(R.id.recycler_chat);
        getUserInfo = getIntent();
    }

    private void listeners() {
        send_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        camera_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}