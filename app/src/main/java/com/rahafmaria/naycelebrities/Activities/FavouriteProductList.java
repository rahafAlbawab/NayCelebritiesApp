package com.rahafmaria.naycelebrities.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.rahafmaria.naycelebrities.Adapter.FavouriteProductListAdapter;
import com.rahafmaria.naycelebrities.Model.FavouriteProductListModel;
import com.rahafmaria.naycelebrities.PathUrls;
import com.rahafmaria.naycelebrities.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FavouriteProductList extends AppCompatActivity {
    RecyclerView favourite_product_recycler;
    ArrayList<FavouriteProductListModel> favouriteProductListModels;
    FavouriteProductListAdapter favouriteProductListAdapter;
    ImageView logout_icon;
    ImageView chat_icon;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static Context context;
    int celebritiesId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_product_list);
        Initialization();
        listeners();
        favourite_product_recycler.setLayoutManager(new LinearLayoutManager(this));
        fillModel();
        favourite_product_recycler.setAdapter(favouriteProductListAdapter);
    }
    private void Initialization() {
        context = FavouriteProductList.this;
        logout_icon = findViewById(R.id.logout_icon);
        chat_icon = findViewById(R.id.chat_icon);
        favourite_product_recycler = findViewById(R.id.favourite_product_recycler);
        favouriteProductListModels = new ArrayList<>();
        favouriteProductListAdapter = new FavouriteProductListAdapter(favouriteProductListModels);
        sharedPreferences = getSharedPreferences("loginCheck", MODE_PRIVATE);
        sharedPreferences = getSharedPreferences("loginCheck", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        celebritiesId = Integer.parseInt(sharedPreferences.getString("user_id",""));

    }
    private void listeners() {
        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FavouriteProductList.this, LoginActivity.class);
                editor.putString("isLogged", "no");
                editor.commit();
                startActivity(intent);

            }
        });
        chat_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FavouriteProductList.this, CelebritiesChatListActivity.class);
                startActivity(intent);
            }
        });


    }
    private void fillModel() {
        String url = PathUrls.baseUrl + PathUrls.getfavorateProductUrl + "?celebrities_id="+celebritiesId;
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                //get data from response and fill it in Array List
                                JSONObject obj = response.getJSONObject(i);
                                favouriteProductListModels.add(new FavouriteProductListModel(
                                        obj.getString("name"),
                                        obj.getString("image"),
                                        obj.getInt("product_id")
                                ));
                                favouriteProductListAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FavouriteProductList.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);


    }

}