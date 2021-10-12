package com.c19soece11022.parivahan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


    public class Registration extends AppCompatActivity {

        DbHelper myDB;
        Button reg;
        EditText ed1,ed2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registration);

            myDB = new DbHelper(this);

            TextView login = findViewById(R.id.log);
            EditText ed1 = findViewById(R.id.d_id);
            EditText ed2 = findViewById(R.id.pass);


            Add();


            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
        public void Add(){
            TextView register = findViewById(R.id.reg_btn);

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    EditText ed1 = findViewById(R.id.d_id);
                    EditText ed2 = findViewById(R.id.pass);
                    EditText m = findViewById(R.id.m_no);
                    String m_no = m.getText().toString();
                    String s3 = ed1.getText().toString();
                    String s4 = ed2.getText().toString();

                    EditText t1 = findViewById(R.id.fname);
                    EditText t2 = findViewById(R.id.lname);
                    RadioButton r1 = findViewById(R.id.male);
                    RadioButton r2 = findViewById(R.id.female);
                    CheckBox c1 = findViewById(R.id.cbox);
                    Spinner c2 = findViewById(R.id.city);


                    String s1 = t1.getText().toString();
                    String s2 = t2.getText().toString();
                    String s5 = "";
                    String s6 = "";
                    String s7 = c2.getSelectedItem().toString();
                    String s8 = "";



                    if(r1.isChecked()){
                        s5="Male";
                    }else if(r2.isChecked()){
                        s5="Female";
                    }else{
                        s5="Other";
                    }





                    if(s1.length()==0 && s2.length()==0 && s3.length()==0 && s4.length()==0){
                        Toast.makeText(com.c19soece11022.parivahan.Registration.this, "Invalid details", Toast.LENGTH_SHORT).show();
                    }
                    else if(s1.length()==0 || s1.length()<3)
                    {
                        Toast.makeText(com.c19soece11022.parivahan.Registration.this, "Invalid First Name", Toast.LENGTH_SHORT).show();
                    }
                    else if(!s1.matches("[a-zA-Z ]+"))
                    {
                        Toast.makeText(com.c19soece11022.parivahan.Registration.this, "First Name contain alphabets only", Toast.LENGTH_SHORT).show();
                        // t1.requestFocus();
                        //t1.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                    }
                    else if(!s2.matches("[a-zA-Z ]+"))
                    {
                        Toast.makeText(com.c19soece11022.parivahan.Registration.this, "Last Name contain alphabets only", Toast.LENGTH_SHORT).show();
                    }
                    else if(s2.length()==0 || s2.length()<3)
                    {
                        Toast.makeText(com.c19soece11022.parivahan.Registration.this, "Invalid Last Name", Toast.LENGTH_SHORT).show();
                    }
                    else if(s4.length()==0 || s4.length()<8){
                        Toast.makeText(com.c19soece11022.parivahan.Registration.this, "Password is at least 8 characters long", Toast.LENGTH_SHORT).show();
                    }
                    else if(m_no.length()!=10)
                    {
                        Toast.makeText(com.c19soece11022.parivahan.Registration.this, "Enter valid mobile no.", Toast.LENGTH_SHORT).show();
                    }
                    else if(!c1.isChecked()){
                        Toast.makeText(com.c19soece11022.parivahan.Registration.this, "Accept terms & Conditions", Toast.LENGTH_SHORT).show();
                    }
                    else if(myDB.AddData(ed1.getText().toString(),ed2.getText().toString())) {
                        Toast.makeText(com.c19soece11022.parivahan.Registration.this, "successfully registered", Toast.LENGTH_SHORT).show();
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Intent a1 = new Intent(com.c19soece11022.parivahan.Registration.this,Login.class);
                                startActivity(a1);
                                finish();
                            }
                        },1000);
                    }
                    else
                    {
                        Toast.makeText(com.c19soece11022.parivahan.Registration.this, "please try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

