package com.example.spotty;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

//This class defines the user profile. once a user gets logged in the needed details of the user will be displayed
public class userProfile extends AppCompatActivity {

    Button logout, resetpsw, editprof;
    TextView name, email;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;
    ImageButton backbtn;
    AlertDialog.Builder reset_alert;
    LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().hide(); //to hide the action bar - name of the app

         name=  findViewById(R.id.nameprof);
         email= findViewById(R.id.emailprof);
         backbtn= findViewById(R.id.back);
         editprof= findViewById(R.id.editprof);

        reset_alert= new AlertDialog.Builder(this);
        inflater= this.getLayoutInflater();
        resetpsw= (Button) findViewById(R.id.resetpw);


        fAuth= FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();
        userID=fAuth.getCurrentUser().getUid();

        DocumentReference documentReference= fstore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                email.setText(documentSnapshot.getString("Email"));
                name.setText(documentSnapshot.getString("Name"));

            }
        });

        logout= (Button) findViewById(R.id.logoutprof);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(userProfile.this, sign_in.class));
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(userProfile.this,menu.class));
            }
        });

        resetpsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //alert
                View view = inflater.inflate(R.layout.reset_password, null);

                reset_alert.setTitle("Why can't you Sign In?")
                        .setMessage("Enter Your Email to get Password Reset link.")
                        .setPositiveButton("Reset", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //validate the email address

                                EditText email= view. findViewById(R.id.reset_email);
                                if(email.getText().toString().isEmpty()){
                                    email.setError("Required Field!");
                                    return;
                                }

                                //send the reset link
                                fAuth.sendPasswordResetEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(userProfile.this, "Reset Email Sent", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(userProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }


                        }).setNegativeButton("Cancel", null)
                        .setView(view)
                        .create().show();
            }
        });

        editprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(userProfile.this,Edit_Profile.class));
            }
        });







    }
}