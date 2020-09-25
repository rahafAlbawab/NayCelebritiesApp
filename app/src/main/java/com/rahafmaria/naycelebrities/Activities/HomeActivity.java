package com.rahafmaria.naycelebrities.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.rahafmaria.naycelebrities.Adapter.HomeViewPagerAdpater;
import com.rahafmaria.naycelebrities.R;

public class HomeActivity extends AppCompatActivity {

    ImageView logout_icon;
    ImageView chat_icon;
    ImageView product_list_icon;
    TabLayout tabLayout;
    ViewPager viewPager;
    HomeViewPagerAdpater homeViewPagerAdpater;
    public static Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Initialization();
        listeners();
        homeViewPagerAdpater = new HomeViewPagerAdpater(getSupportFragmentManager());
        viewPager.setAdapter(homeViewPagerAdpater);
        tabLayout.setupWithViewPager(viewPager);
    }


    private void Initialization() {
        logout_icon = findViewById(R.id.logout_icon);
        chat_icon = findViewById(R.id.chat_icon);
        product_list_icon = findViewById(R.id.product_list_icon);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        context = HomeActivity.this;
        sharedPreferences = getSharedPreferences("loginCheck", MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }


    private void listeners() {
        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                editor.putString("isLogged", "no");
                editor.commit();
                startActivity(intent);

            }
        });
        chat_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CelebritiesChatListActivity.class);
                intent.putExtra("activity_name","HomeActivity");
                startActivity(intent);
            }
        });
        product_list_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, FavouriteProductListActivity.class);
                intent.putExtra("activity_name","HomeActivity");
                startActivity(intent);
            }
        });

    }


}