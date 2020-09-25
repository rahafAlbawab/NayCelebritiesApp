package com.rahafmaria.naycelebrities.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.rahafmaria.naycelebrities.Adapter.CelebritiesChatListAdapter;
import com.rahafmaria.naycelebrities.Model.CelebritiesChatListModel;
import com.rahafmaria.naycelebrities.PathUrls;
import com.rahafmaria.naycelebrities.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CelebritiesChatListActivity extends AppCompatActivity {
    ImageView logout_icon;
    ImageView product_list_icon;
    ImageView arrow_icon;
    RecyclerView chat_list_recycler;
    ArrayList<CelebritiesChatListModel> celebritiesChatListModels;
    CelebritiesChatListAdapter celebritiesChatListAdapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Intent mainIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrities_chat_list);
        Initialization();
        listeners();
        chat_list_recycler.setLayoutManager(new LinearLayoutManager(this));
        fillModel();
        chat_list_recycler.setAdapter(celebritiesChatListAdapter);

    }

    private void Initialization() {
        mainIntent = getIntent();
        logout_icon = findViewById(R.id.logout_icon);
        product_list_icon = findViewById(R.id.product_list_icon);
        arrow_icon = findViewById(R.id.arrow_icon);
        chat_list_recycler = findViewById(R.id.chat_list_recycler);
        sharedPreferences = getSharedPreferences("loginCheck", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        celebritiesChatListModels = new ArrayList<>();
        celebritiesChatListAdapter = new CelebritiesChatListAdapter(celebritiesChatListModels, this);

    }

    private void fillModel() {

        final String url = PathUrls.baseUrl + PathUrls.getCelebritiesUrl + "?user_type=0";
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("resUser", response.length() + "");

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                //get data from response and fill it in Array List
                                JSONObject obj = response.getJSONObject(i);


                                celebritiesChatListModels.add(new CelebritiesChatListModel(
                                        obj.getString("name"),
                                        obj.getString("user_image"),
                                        "hello",
                                        obj.getInt("user_id")));
                                celebritiesChatListAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CelebritiesChatListActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
        requestQueue.add(jsonArrayRequest);

    }

    private void listeners() {
        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CelebritiesChatListActivity.this, LoginActivity.class);
                editor.putString("isLogged", "no");
                editor.commit();
                startActivity(intent);

            }
        });

        product_list_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CelebritiesChatListActivity.this, FavouriteProductListActivity.class);
                intent.putExtra("activity_name", "CelebritiesChatListActivity");
                startActivity(intent);
            }
        });
        arrow_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if (mainIntent.getStringExtra("activity_name").equals("HomeActivity")) {
                    intent = new Intent(CelebritiesChatListActivity.this, HomeActivity.class);
                } else if (mainIntent.getStringExtra("activity_name").equals("FavouriteProductListActivity")) {
                    intent = new Intent(CelebritiesChatListActivity.this, FavouriteProductListActivity.class);
                } else {
                    intent = new Intent(CelebritiesChatListActivity.this, CelebritiesChatActivity.class);
                }

                startActivity(intent);
            }
        });
    }
}