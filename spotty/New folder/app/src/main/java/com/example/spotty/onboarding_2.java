package com.example.spotty;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class onboarding_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding2);
        getSupportActionBar().hide(); //to hide the action bar - name of the app
        ImageView imageView9 = (ImageView)findViewById(R.id.imageView9);
        imageView9.setImageAlpha(80);
    }
}