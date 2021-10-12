package com.c19soece11022.parivahan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FuelDataEntry extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String PrefName = "myLogout";
    String key = "Uname";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_data_entry);
        preferences = getSharedPreferences(PrefName,MODE_PRIVATE);
        editor = preferences.edit();

        EditText date =findViewById(R.id.e1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateandTime = sdf.format(new Date());
        date.setText(currentDateandTime);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.logout){
            editor.remove(key);
            editor.commit();

            Intent a = new Intent(FuelDataEntry.this,Login.class);
            startActivity(a);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void cliclAdd(View view) {

        EditText e1 = findViewById(R.id.e1);
        EditText e2 = findViewById(R.id.e2);
        EditText e3 = findViewById(R.id.e3);
        EditText e4 = findViewById(R.id.e4);
        EditText e5 = findViewById(R.id.e5);

        String s1 = e1.getText().toString();
        String s2 = e2.getText().toString();
        String s3 = e3.getText().toString();
        String s4 = e4.getText().toString();
        String s5 = e5.getText().toString();

        Intent a = new Intent(FuelDataEntry.this,ConfirmFuelData.class);
        a.putExtra("s1",s1);
        a.putExtra("s2",s2);
        a.putExtra("s3",s3);
        a.putExtra("s4",s4);
        a.putExtra("s5",s5);
        startActivity(a);
        finish();
        

    }

}