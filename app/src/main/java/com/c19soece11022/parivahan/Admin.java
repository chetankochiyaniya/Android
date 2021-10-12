package com.c19soece11022.parivahan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Admin extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String PrefName = "myLogout";
    String key = "admin";
    FuelDbHelper cDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        preferences = getSharedPreferences(PrefName,MODE_PRIVATE);
        editor = preferences.edit();

        cDB = new FuelDbHelper(this);
        viewData();
        viewSum();

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

            Intent a = new Intent(Admin.this,Login.class);
            startActivity(a);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



    public void viewSum(){
        TextView test = findViewById(R.id.btnsum);
        test.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int res = cDB.getAllPriceSum();

                        // Show all data
                        showMessage("Total fuel cost :\n"+res);
                    }
                }
        );
    }

    public void showMessage(String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Fuel Cost");
        builder.setMessage(Message);
        builder.show();
    }




    public void viewData(){
        TextView test = findViewById(R.id.btnview);
        test.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = cDB.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("\nId :"+ res.getString(0)+"\n");
                            buffer.append("Date :"+ res.getString(1)+"\n");
                            buffer.append("Vehicle No :"+ res.getString(2)+"\n");
                            buffer.append("Odometer :"+ res.getString(3)+"\n");
                            buffer.append("Fuel (Lr.) :"+ res.getString(4)+"\n");
                            buffer.append("Price(Rs.) :"+ res.getString(5)+"\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}