package com.example.spotty;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class onboarding_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding3);
        getSupportActionBar().hide(); //to hide the action bar - name of the app
    }
}