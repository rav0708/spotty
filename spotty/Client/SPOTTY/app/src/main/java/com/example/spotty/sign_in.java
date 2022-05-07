package com.example.spotty;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//User sign-in class
public class sign_in extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private TextView register, forgotpassword;
    TextView email, password;
    private ProgressBar progressBar;
    AlertDialog.Builder reset_alert;
    LayoutInflater inflater;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        reset_alert= new AlertDialog.Builder(this);
        inflater= this.getLayoutInflater();
        register= (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
        forgotpassword= (TextView) findViewById(R.id.forgotpsw);


        //intialization of the login btn
        Button login=findViewById(R.id.adminbtn);
        login.setOnClickListener(this);

        email= findViewById(R.id.log_email);
        password=findViewById(R.id.log_psw);

        progressBar= (ProgressBar) findViewById(R.id.progressBar);

        getSupportActionBar().hide(); //to hide the action bar - name of the app


        forgotpassword.setOnClickListener(new View.OnClickListener() {
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
                                mAuth.sendPasswordResetEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(sign_in.this, "Reset Email Sent", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(sign_in.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }


                        }).setNegativeButton("Cancel", null)
                          .setView(view)
                        .create().show();
            }
        });

       login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              userLogin();

         }
       });



    }

    @Override
    public void onClick(View view) {
                startActivity(new Intent(this, sign_up.class));

    }

    private void userLogin() {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();

        if (Email.isEmpty()) {
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            email.setError("Please provide a valid email");
            email.requestFocus();
            return;
        }

        if (Password.isEmpty()) {
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }
        if (Password.length() < 6) {
            password.setError("Minimum password length should be 6 characters");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //redirect to user profile
                    Toast.makeText(sign_in.this, "Signed In!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(sign_in.this, questionnaire.class));
                    progressBar.setVisibility(View.GONE);
                    finish();
                } else {
                    Toast.makeText(sign_in.this, "Failed to Sign In! The account does not exist", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }



    }


