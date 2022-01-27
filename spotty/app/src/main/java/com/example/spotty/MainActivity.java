package com.example.spotty;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    Handler h=new Handler();//creates a new handler to delay
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); //to hide the action bar - name of the app
        h.postDelayed(new Runnable() {   //this will delay the execution time of a particular code
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this, reg_login.class);//creating a new intent to go into another interface
                startActivity(intent);
                finish();// to prevent quitting from the app when going back


            }
        },3000);//seconds in thousands


    }
}