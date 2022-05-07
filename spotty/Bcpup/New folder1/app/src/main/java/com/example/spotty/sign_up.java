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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


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
        regbtn = findViewById(R.id.logbtnn);
        userName=findViewById(R.id.name);
        email=findViewById(R.id.log_email);
        password=findViewById(R.id.log_psw);
        confirmPass=findViewById(R.id.confpsw);

//        user= FirebaseDatabase.getInstance().getReference().child("Users");//making the table under the name of USERS

        progressBar1= (ProgressBar) findViewById(R.id.progressBar1);









        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ask_loc();// to ask user permission in the app and if it is false
                //Toast.makeText(MainScreen.this, "The Picture is captured", Toast.LENGTH_SHORT).show();
                //onStart();
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

//                                 FirebaseFirestore database = FirebaseFirestore.getInstance();
//                                 DatabaseReference myRef = database.collection("message");
                                    //putting into info class
//                                    info info=new info(Name,Email,Password);
//                                    info.signup();

//                                 // Create a new user with a first and last name
//                                 Map<String, Object> user = new HashMap<>();
//                                 user.put("first", "Ada");
//                                 user.put("last", "Lovelace");
//                                 user.put("born", 1815);

// Add a new document with a generated ID
//                                 db.collection("users")
//                                         .add(user)
//                                         .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                             @Override
//                                             public void onSuccess(DocumentReference documentReference) {
//                                                 Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                                             }
//                                         })
//                                         .addOnFailureListener(new OnFailureListener() {
//                                             @Override
//                                             public void onFailure(@NonNull Exception e) {
//                                                 Log.w(TAG, "Error adding document", e);
//                                             }
//                                         });

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
                                                             Toast.LENGTH_SHORT).show();
                                                     String uID =user.getUid();

                                                     Map<String, Object> newuser = new HashMap<>();
                                                     newuser.put("Name", Name);
                                                     newuser.put("Email", Email);


                                                     //specify if the user is an admin.
                                                     //newuser.put("isUser", "1");

                                 db.collection("users").document(uID)
                                         .set(newuser);





//                                         .addOnSuccessListener(new OnSuccessListener<Void>(){
//                                             @Override
//                                             public void onSuccess(DocumentReference documentReference) {
//                                                 Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                                             }
//                                         })
//                                         .addOnFailureListener(new OnFailureListener() {
//                                             @Override
//                                             public void onFailure(@NonNull Exception e) {
//                                                 Log.w(TAG, "Error adding document", e);
//                                             }
//                                         });
                                                     progressBar1.setVisibility(View.GONE);
                                                 } else {
                                                     // If sign in fails, display a message to the user.
                                                     System.out.println("createUserWithEmail:failure"+ task.getException());
                                                     Log.d(TAG, "createUserWithEmail:failure"+ task.getException());
                                                    Toast.makeText(sign_up.this, "Authentication failed.",
                                                            Toast.LENGTH_SHORT).show();
                                                     progressBar1.setVisibility(View.GONE);

                                                 }
                                             }
                                         });

                                    //pushing into FB
//                                 myRef.setValue("Hello, World!");
//                                    user.push().setValue(info);
//                                    userName.setText(" ");
//                                    email.setText(" ");
//                                    password.setText(" ");














}


    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, sign_in.class));
    }


}