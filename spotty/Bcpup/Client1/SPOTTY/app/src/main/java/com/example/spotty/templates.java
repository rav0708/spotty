package com.example.spotty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Adapter;

public class templates extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);

        getSupportActionBar().hide(); //to hide the action bar - name of the app

        ViewPager viewPager=findViewById(R.id.viewPagertemp);

        adapter adapter=new adapter(this);
        viewPager.setAdapter(adapter);

    }
}