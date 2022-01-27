package com.example.spotty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class reg_login extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_login);
        getSupportActionBar().hide(); //to hide the action bar - name of the app
        ImageView mobile = (ImageView)findViewById(R.id.mobile);
        mobile.setImageAlpha(70);
        //intialization of the signup btn
          Button reg=findViewById(R.id.signup);


        //intialization of the signin btn
        Button reg_1=findViewById(R.id.signin);

        //onclick acton listener fir the signup btn
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg=new Intent(reg_login.this,sign_up.class);
                startActivity(reg);
               // finish();
            }
        });

        reg_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg_1=new Intent(reg_login.this,sign_in.class);
                startActivity(reg_1);
               finish();
            }
        });
    }
}