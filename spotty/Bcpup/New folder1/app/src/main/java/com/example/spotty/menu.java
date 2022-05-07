package com.example.spotty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class menu extends AppCompatActivity {
    ImageButton launch_camera, info;
    com.google.android.material.floatingactionbutton.FloatingActionButton profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide(); //to hide the action bar - name of the app
        launch_camera=findViewById(R.id.launch_cam);
         info=findViewById(R.id.info);

        launch_camera.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 //startActivity(new Intent(menu.this,camlaunch.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                 startActivity(new Intent(menu.this,camlaunch.class));
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


}
}