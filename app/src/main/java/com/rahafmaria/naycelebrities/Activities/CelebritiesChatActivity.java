package com.rahafmaria.naycelebrities.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rahafmaria.naycelebrities.Adapter.CelebritiesChatAdapter;
import com.rahafmaria.naycelebrities.Database.LocalDB;
import com.rahafmaria.naycelebrities.Database.RemoteDB;
import com.rahafmaria.naycelebrities.Model.CelebritiesChatModel;
import com.rahafmaria.naycelebrities.R;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;

public class CelebritiesChatActivity extends AppCompatActivity {
    ImageView camera_chat;
    ImageView send_chat;
    ImageView arrow_icon;
    ImageView user_image;
    TextView user_name;
    EditText text_chat;
    RecyclerView recycler_chat;
    ArrayList<CelebritiesChatModel> celebritiesChatModels;
    CelebritiesChatAdapter celebritiesChatAdapter;
    SharedPreferences sharedPreferences;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    Intent getUserInfo;
    String userName;
    String userImage;
    public static int user_id;
    RemoteDB remoteDatabase;
    LocalDB localDB;
    Uri filePath;
    String last_message_receive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrities_chat);
        Initialization();
        listeners();
        if (getUserInfo.getStringExtra("user_name") != null
                && getUserInfo.getStringExtra("user_image") != null
                && getUserInfo.getIntExtra("user_id", 0) != 0) {
            Log.d("getUserInfo", getUserInfo.getStringExtra("user_image"));
            Log.d("getUserInfo", getUserInfo.getStringExtra("user_name"));
            userName = getUserInfo.getStringExtra("user_name");
            userImage = getUserInfo.getStringExtra("user_image");
            user_id = getUserInfo.getIntExtra("user_id", 0);
            user_name.setText(userName);
            Picasso.get().load(userImage).into(user_image);

        }

        recycler_chat.setLayoutManager(new LinearLayoutManager(this));
        celebritiesChatModels.addAll(localDB.selectToTableNayDB());
        recycler_chat.setAdapter(celebritiesChatAdapter);
        recycler_chat.scrollToPosition(celebritiesChatModels.size() - 1);
        for (int i = celebritiesChatModels.size() - 1 ; i > 0 ;i--){
            if (celebritiesChatModels.get(i).user_id != Integer.parseInt(sharedPreferences.getString("user_id", "0"))){
                last_message_receive = celebritiesChatModels.get(i).message;
                return;
            }

        }

            databaseReference.child(user_id + "").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (!dataSnapshot.child("message").getValue().toString().equals("")) {
                        if (dataSnapshot.child("receiver_id").getValue().toString().equals(sharedPreferences.getString("user_id", "0"))) {
                            if ((celebritiesChatModels.size() != 0) &&
                                    (!dataSnapshot.child("message").getValue().toString().equals(last_message_receive))) {
                                celebritiesChatModels.add(new CelebritiesChatModel(user_id,
                                        dataSnapshot.child("message").getValue().toString(),
                                        Integer.parseInt(dataSnapshot.child("type").getValue().toString())));
                                localDB.insertToTableNayDB(user_id,
                                        Integer.parseInt(sharedPreferences.getString("user_id", "0")),
                                        dataSnapshot.child("message").getValue().toString(),
                                        Integer.parseInt(dataSnapshot.child("type").getValue().toString()));
                                celebritiesChatAdapter.notifyDataSetChanged();
                                recycler_chat.scrollToPosition(celebritiesChatModels.size() - 1);
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


    }

    private void Initialization() {
        camera_chat = findViewById(R.id.camera_chat);
        arrow_icon = findViewById(R.id.arrow_icon);
        user_image = findViewById(R.id.user_image);
        user_name = findViewById(R.id.user_name);
        send_chat = findViewById(R.id.send_chat);
        text_chat = findViewById(R.id.text_chat);
        sharedPreferences = getSharedPreferences("loginCheck", MODE_PRIVATE);
        recycler_chat = findViewById(R.id.recycler_chat);
        celebritiesChatModels = new ArrayList<>();
        celebritiesChatAdapter = new CelebritiesChatAdapter(celebritiesChatModels, this, Integer.parseInt(sharedPreferences.getString("user_id", "0")));
        getUserInfo = getIntent();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("chat");
        storageReference = FirebaseStorage.getInstance().getReference();
        remoteDatabase = new RemoteDB(this);
        localDB = new LocalDB(this);

    }

    private void listeners() {
        send_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!text_chat.getText().toString().equals("")) {
                    //1-Store in Firebase Database

                    databaseReference.child(sharedPreferences.getString("user_id", "0"))
                            .child("message").setValue(text_chat.getText().toString());
                    databaseReference.child(sharedPreferences.getString("user_id", "0"))
                            .child("type").setValue(1);
                    databaseReference.child(sharedPreferences.getString("user_id", "0"))
                            .child("receiver_id").setValue(user_id);
                    //2- Store in Remote Database
                    remoteDatabase.insertChatTable(Integer.parseInt(sharedPreferences.getString("user_id", "0")),
                            user_id,
                            text_chat.getText().toString(),
                            1);

                    //3- store in Local Database
                    boolean cheks = localDB.insertToTableNayDB(Integer.parseInt(sharedPreferences.getString("user_id", "0")),
                            user_id,
                            text_chat.getText().toString(),
                            1);
                    Log.d("checkifadd", cheks + "");
                    //4- store in ArrayList

                    celebritiesChatModels.add(new CelebritiesChatModel(Integer.parseInt(sharedPreferences.getString("user_id", "0")),
                            text_chat.getText().toString(), 1));

                    celebritiesChatAdapter.notifyDataSetChanged();
                    recycler_chat.scrollToPosition(celebritiesChatModels.size() - 1);

                    text_chat.setText("");

                }

            }
        });
        camera_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(CelebritiesChatActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                filePath = result.getUri();

                String name = databaseReference.push().getKey();
                final StorageReference ref = storageReference.child("Images/" + name);
                ref.putFile(filePath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()) {
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //4- Store in ArrayList

                                    celebritiesChatModels.add(new CelebritiesChatModel(Integer.parseInt(sharedPreferences.getString("user_id", "0")),
                                            uri.toString(), 2));

                                    celebritiesChatAdapter.notifyDataSetChanged();
                                    recycler_chat.scrollToPosition(celebritiesChatModels.size() - 1);

                                    //1-store in RealTime DB
                                    databaseReference.child(sharedPreferences.getString("user_id", "0"))
                                            .child("message").setValue(uri.toString());
                                    databaseReference.child(sharedPreferences.getString("user_id", "0"))
                                            .child("type").setValue(2);
                                    databaseReference.child(sharedPreferences.getString("user_id", "0"))
                                            .child("receiver_id").setValue(user_id);
                                    //2- Store in Remote Database
                                    remoteDatabase.insertChatTable(Integer.parseInt(sharedPreferences.getString("user_id", "0")),
                                            user_id,
                                            uri.toString(),
                                            2);

                                    //3- store in Local Database
                                    boolean cheks = localDB.insertToTableNayDB(Integer.parseInt(sharedPreferences.getString("user_id", "0")),
                                            user_id,
                                            uri.toString(),
                                            2);
                                    Log.d("checkifadd", cheks + "");


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });

                        } else {
                            Toast.makeText(CelebritiesChatActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
}