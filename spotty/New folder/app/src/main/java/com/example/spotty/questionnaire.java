package com.example.spotty;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

//import android.widget.Spinner;

public class questionnaire extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        getSupportActionBar().hide(); //to hide the action bar - name of the app


       /* Spinner BUILDING_TYPE=findViewById(R.id.BUILDING_TYPE);

        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.BUILDING_TYPE, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        BUILDING_TYPE.setAdapter(adapter);*/


        //intialization of the submit btn
        Button menu=findViewById(R.id.submit);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu=new Intent(questionnaire.this,menu.class);
                startActivity(menu);
                //finish();
            }
        });

    }
}