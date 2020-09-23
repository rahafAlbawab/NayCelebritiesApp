package com.rahafmaria.naycelebrities.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.rahafmaria.naycelebrities.Adapter.HairAdapter;
import com.rahafmaria.naycelebrities.Model.HairModels;
import com.rahafmaria.naycelebrities.PathUrls;
import com.rahafmaria.naycelebrities.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HairCareFragment extends Fragment {
    RecyclerView hair_recyclerView;
    ArrayList<HairModels> hairModels;
    HairAdapter hairAdapter;


    public HairCareFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hair_care, container, false);
        hair_recyclerView = view.findViewById(R.id.hire_recyclerview);

        hairModels = new ArrayList<>();

        hairAdapter = new HairAdapter(hairModels);
        hair_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fillList();
        hair_recyclerView.setAdapter(hairAdapter);
        return view;
    }
    private void fillList() {
        String url = PathUrls.baseUrl + PathUrls.getProductsUrl + "?category=hair";
        final RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                hairModels.add(new HairModels(obj.getString("image"),
                                        obj.getString("name"),
                                        obj.getInt("price"),
                                        obj.getInt("product_id")));
                                hairAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
        requestQueue.add(jsonArrayRequest);

    }
}