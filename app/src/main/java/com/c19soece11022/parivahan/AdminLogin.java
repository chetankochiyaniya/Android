package com.c19soece11022.parivahan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLogin extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String PrefName = "myLogout";
    String key = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        getSupportActionBar().setTitle("Admin");

        preferences = getSharedPreferences(PrefName, MODE_PRIVATE);
        editor = preferences.edit();

        String ValUname = preferences.getString(key, "");
        if (!ValUname.equals("")) {
            Intent c1 = new Intent(AdminLogin.this, Admin.class);
            startActivity(c1);
            finish();
        }
    }

    public void clickLogin(View view) {
        EditText e1 = findViewById(R.id.uname);
        EditText e2 = findViewById(R.id.pass);

        String s1 = e1.getText().toString();
        String s2 = e2.getText().toString();


        if (s1.equals("Chetan")&&s2.equals("Chetan@11022")) {
            editor.putString(key, s1);
            editor.commit();
            Intent a1 = new Intent(AdminLogin.this, Admin.class);
            startActivity(a1);
            Toast.makeText(this, "login successfully", Toast.LENGTH_SHORT).show();
            finish();

        } else {
            Toast.makeText(this, "please enter valid details", Toast.LENGTH_SHORT).show();

        }
    }
}