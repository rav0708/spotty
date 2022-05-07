package com.example.spotty;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

//User sign-up class
public class sign_up extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView link;
    private static final String TAG = "U_details";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button regbtn;
    TextView userName, email, password, confirmPass;
    private ProgressBar progressBar1;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);
        link= (TextView) findViewById(R.id.link);
        link.setOnClickListener(this);
        regbtn = findViewById(R.id.adminbtn);
        userName=findViewById(R.id.name);
        email=findViewById(R.id.log_email);
        password=findViewById(R.id.log_psw);
        confirmPass=findViewById(R.id.confpsw);

//        user= FirebaseDatabase.getInstance().getReference().child("Users");//making the table under the name of USERS

        progressBar1= (ProgressBar) findViewById(R.id.progressBar1);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User_print();


            }
        });

    }
                             private void User_print() {

                                    Log.d(TAG,"Name: "+userName.toString());
                                    Log.d(TAG,"Email: "+email.toString());
                                    Log.d(TAG ,"Password"+password.toString());
                                    Log.d(TAG ,"Confirm Password"+confirmPass.toString());


                                    String Name=userName.getText().toString().trim();
                                    String Email=email.getText().toString().trim();
                                    String Password=password.getText().toString().trim();
                                    String Confirm_Password=confirmPass.getText().toString().trim();

                                    if(Name.isEmpty()){
                                        userName.setError("Name is required!");
                                    }

                                    if(Email.isEmpty()){
                                        email.setError("Email is required!");
                                        email.requestFocus();
                                        return;
                                    }

                                    if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                                        email.setError("Please provide a valid email");
                                        email.requestFocus();
                                        return;
                                    }

                                    if(Password.isEmpty()){
                                        password.setError("Password is required!");
                                        password.requestFocus();
                                        return;
                                    }
                                    if(Password.length()<6){
                                        password.setError("Minimum password length should be 6 characters");
                                        password.requestFocus();
                                        return;
                                    }
                                    if(!Password.equals(Confirm_Password)){
                                        confirmPass.setError("Password does not match!");
                                        confirmPass.requestFocus();
                                        return;
                                    }

                                 progressBar1.setVisibility(View.VISIBLE);



                                 mAuth = FirebaseAuth.getInstance();

                                 //create new user
                                 mAuth.createUserWithEmailAndPassword(Email, Password)
                                         .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                             @Override
                                             public void onComplete(@NonNull Task<AuthResult> task) {
                                                 if (task.isSuccessful()) {
                                                     startActivity(new Intent(sign_up.this,sign_in.class));
                                                     // Sign in success, update UI with the signed-in user's information
                                                     System.out.println("createUserWithEmail:success");
                                                     Log.d(TAG, "createUserWithEmail:success");
                                                     FirebaseUser user = mAuth.getCurrentUser();
                                                     Toast.makeText(sign_up.this, "Authentication success.",
                                                             Toast.LENGTH_LONG).show();
                                                     String uID =user.getUid();

                                                     Map<String, Object> newuser = new HashMap<>();
                                                     newuser.put("Name", Name);
                                                     newuser.put("Email", Email);


                                 db.collection("users").document(uID)
                                         .set(newuser);

                                                     progressBar1.setVisibility(View.GONE);
                                                 }
                                                 else {
                                                     // If sign in fails, display a message to the user.
                                                     System.out.println("createUserWithEmail:failure"+ task.getException());
                                                     Log.d(TAG, "createUserWithEmail:failure"+ task.getException());
                                                     Toast.makeText(sign_up.this, "Authentication failed.",
                                                            Toast.LENGTH_SHORT).show();
                                                     progressBar1.setVisibility(View.GONE);

                                                 }
                                             }
                                         });






}


    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, sign_in.class));
    }


}