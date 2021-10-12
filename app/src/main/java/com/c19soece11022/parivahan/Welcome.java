package com.c19soece11022.parivahan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class Welcome extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().setTitle("Welcome");

        imageView = findViewById(R.id.imageView);
        // Adding the gif here using glide library
        Glide.with(this).load(R.drawable.user_logo).into(imageView);

        String s1 = getIntent().getStringExtra("uname");
        TextView t1 = (TextView) findViewById(R.id.wel_text);
        t1.setText("welcome, "+ s1);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent a1 = new Intent(Welcome.this,FuelDataEntry.class);
                startActivity(a1);
                finish();
            }
        },2000);
    }
}