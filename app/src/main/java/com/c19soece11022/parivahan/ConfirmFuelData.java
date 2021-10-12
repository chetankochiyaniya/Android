package com.c19soece11022.parivahan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ConfirmFuelData extends AppCompatActivity {

    FuelDbHelper cDB;
    String s1,s2,s3,s4,s5;
    TextView t1,t2,t3,t4,t5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_fuel_data);


        String s1 = getIntent().getStringExtra("s1");
        String s2 = getIntent().getStringExtra("s2");
        String s3 = getIntent().getStringExtra("s3");
        String s4 = getIntent().getStringExtra("s4");
        String s5 = getIntent().getStringExtra("s5");


        TextView t1 = findViewById(R.id.tv1);
        TextView t2 = findViewById(R.id.tv2);
        TextView t3 = findViewById(R.id.tv3);
        TextView t4 = findViewById(R.id.tv4);
        TextView t5 = findViewById(R.id.tv5);


        t1.setText(s1);
        t2.setText(s2);
        t3.setText(s3);
        t4.setText(s4);
        t5.setText(s5);


        cDB = new FuelDbHelper(this);
        //Add_fuel_date();

        TextView conf = findViewById(R.id.btnCon);

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (cDB.AddData(s1, s2, Double.valueOf(s3), Double.valueOf(s4), Double.valueOf(s5))) {
                    Toast.makeText(ConfirmFuelData.this, "successfully registered", Toast.LENGTH_SHORT).show();
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Intent a1 = new Intent(ConfirmFuelData.this, FuelDataEntry.class);
                            startActivity(a1);
                            finish();
                        }
                    }, 1000);
                } else {
                    Toast.makeText(ConfirmFuelData.this, "please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });


}


/*
    private void Add_fuel_date() {

        TextView conf = findViewById(R.id.btnCon);

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView t1 = findViewById(R.id.e1);
                TextView t2 = findViewById(R.id.e2);
                TextView t3 = findViewById(R.id.e3);
                TextView t4 = findViewById(R.id.e4);
                TextView t5 = findViewById(R.id.e5);

                if(cDB.AddData(t1.getText().toString(),t2.getText().toString(), Double.valueOf(t3.getText().toString()), Double.valueOf(t4.getText().toString()), Double.valueOf(t5.getText().toString()))) {
                    Toast.makeText(ConfirmFuelData.this, "successfully registered", Toast.LENGTH_SHORT).show();
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Intent a1 = new Intent(ConfirmFuelData.this,FuelDataEntry.class);
                            startActivity(a1);
                            finish();
                        }
                    },1000);
                }
                else
                {
                    Toast.makeText(ConfirmFuelData.this, "please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }*/
}