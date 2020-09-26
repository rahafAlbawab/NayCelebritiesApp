package com.rahafmaria.naycelebrities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rahafmaria.naycelebrities.Activities.HomeActivity;
import com.rahafmaria.naycelebrities.Activities.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    Animation splah_logo_anim;
    Animation splah_name_anim;
    ImageView logo_image;
    ImageView logo_name;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // To make splash Activity full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        // Animation Initialization
        splah_logo_anim = AnimationUtils.loadAnimation(this, R.anim.splash_logo_anim);
        splah_name_anim = AnimationUtils.loadAnimation(this, R.anim.splash_name_anim);

        //Views Initialization
        logo_image = findViewById(R.id.logo_image);
        logo_name = findViewById(R.id.logo_name);

        //set Animation
        logo_image.setAnimation(splah_logo_anim);
        logo_name.setAnimation(splah_name_anim);


        sharedPreferences = getSharedPreferences("loginCheck", MODE_PRIVATE);

        Thread thread = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(3 * 1000);

                    // After 3 seconds redirect to login Activities


                    if (sharedPreferences.getString("isLogged", "").equalsIgnoreCase("yes")) {
                        Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(i);
                    }

                    //Remove activity => to no back to it
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        thread.start();
    }
}