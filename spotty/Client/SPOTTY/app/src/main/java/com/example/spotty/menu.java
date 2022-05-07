package com.example.spotty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//This class defines the main menu of the entire mobile application which contains different options for a user to select
public class menu extends AppCompatActivity {
    ImageButton launch_camera, info, colours,stores, suggested,template;
   // Button cs;

    com.google.android.material.floatingactionbutton.FloatingActionButton profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide(); //to hide the action bar - name of the app
        launch_camera=findViewById(R.id.launch_cam);
         info=findViewById(R.id.info);
         colours=findViewById(R.id.colours);
         stores=findViewById(R.id.store);
        suggested=findViewById(R.id.suggested);
        template=findViewById(R.id.imageButton11);

//        cs= (Button) findViewById(R.id.cs);
//        cs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(menu.this,suggestedcolours.class));
//            }
//        });

        template.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent template=new Intent(menu.this,templates.class);
                startActivity(template);
            }
        });


        launch_camera.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 //startActivity(new Intent(menu.this,camlaunch.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                 //startActivity(new Intent(menu.this,camlaunch.class));
                 String send2=getIntent().getStringExtra("send");
                 Intent intent = new Intent(menu.this,camlaunch.class );
                 intent.putExtra("send2",send2);
                 startActivity(intent);

             }
         });

        profile=findViewById(R.id.floatingActionButton);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg_1=new Intent(menu.this,userProfile.class);
                startActivity(reg_1);
                //finish();
            }


    });


        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this,UserManual.class));
            }
        });

        colours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this,colour_pallet.class));
            }
        });

        stores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this,googlemap.class));
            }
        });

        suggested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menu.this,suggestedcolours.class));
            }
        });

    }
}