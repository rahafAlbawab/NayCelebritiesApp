package com.rahafmaria.naycelebrities.Database;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rahafmaria.naycelebrities.PathUrls;

import java.util.HashMap;
import java.util.Map;


public class RemoteDB {
    Context context;
    public RemoteDB(Context context) {
        this.context = context;
    }

    public void addFavouriteProduct(final int product_id , final int celebrity_id){
        String url = PathUrls.baseUrl+PathUrls.addFavouriteProductUrl;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responseAddFavouriteProduct",response);
                if(response.equals("alreadyExists")){
                    Toast.makeText(context,"This Product is already exist in your Favourite list",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("product_id", product_id + "");
                params.put("celebrities_id", celebrity_id + "");

                return params;
            }


        };
        requestQueue.add(stringRequest);


    }
    public void removeProductFromFavouriteList(final int product_id , final int celebrities){
        String url = PathUrls.baseUrl + PathUrls.removeProductFromFavouriteListUrl;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("product_id", product_id + "");
                params.put("celebrities_id", celebrities + "");

                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

}
