package com.c19soece11022.parivahan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {


    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String PrefName = "myLogout";
    String key = "Uname";


    DbHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDB = new DbHelper(this);

        preferences = getSharedPreferences(PrefName, MODE_PRIVATE);
        editor = preferences.edit();

        String ValUname = preferences.getString(key, "");
        String ValUname1 = preferences.getString("admin", "");
        if (!ValUname.equals("")) {
            Intent c1 = new Intent(Login.this, FuelDataEntry.class);
            startActivity(c1);
            finish();
        } else if (!ValUname1.equals("")) {
            Intent c1 = new Intent(Login.this, Admin.class);
            startActivity(c1);
            finish();
        }


        TextView register = findViewById(R.id.reg);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
                finish();
            }
        });

              TextView chk = findViewById(R.id.chk);
            chk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // redirect to RegisterActivity
                    Intent intent = new Intent(getApplicationContext(), AdminLogin.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

    public void clickLogin(View view) {
        EditText t1 = findViewById(R.id.uname);
        EditText t2 = findViewById(R.id.pass);
        String s1 = t1.getText().toString();
        String s2 = t2.getText().toString();

        if (myDB.checkUser(s1, s2)) {
            editor.putString(key, s1);
            editor.commit();
            Intent a1 = new Intent(Login.this, Welcome.class);
            a1.putExtra("uname", s1);
            startActivity(a1);
            Toast.makeText(this, "login successfully", Toast.LENGTH_SHORT).show();
            finish();

        } else {
            Toast.makeText(this, "please enter valid details", Toast.LENGTH_SHORT).show();

        }

    }
}