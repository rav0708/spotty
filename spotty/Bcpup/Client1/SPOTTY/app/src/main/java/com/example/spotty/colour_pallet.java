package com.example.spotty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

//This class defines the initiation of the system colour pallet
public class colour_pallet extends AppCompatActivity {

    ImageButton colourText, suggest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_pallet);
        getSupportActionBar().hide(); //to hide the action bar - name of the app
        colourText=findViewById(R.id.colours);
        suggest=findViewById(R.id.suggest);


        suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent suggest=new Intent(colour_pallet.this,suggestedcolours.class);
                startActivity(suggest);
                // finish();


            }        });



    }
}