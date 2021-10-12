package com.c19soece11022.parivahan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        imageView = findViewById(R.id.imageView);
        // Adding the gif here using glide library
        Glide.with(this).load(R.drawable.s_screen).into(imageView);




        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent a1 = new Intent(SplashScreen.this,Login.class);
                startActivity(a1);
                finish();
            }
        },3000);

    }
}