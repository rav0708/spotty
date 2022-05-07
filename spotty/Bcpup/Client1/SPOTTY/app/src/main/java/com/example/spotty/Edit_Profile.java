package com.example.spotty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

//This class is used to develop the edit profile allowing the user modify/change their details such like the user name.
public class Edit_Profile extends AppCompatActivity {

    //Variable initialization
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userID;
    ImageButton backbtn;
    Button updatebtn;
    EditText existingname, newName;
    DocumentReference documentReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().hide(); //to hide the action bar - name of the app
        existingname=findViewById(R.id.ExistingName);
        newName=findViewById(R.id.NewName);
        updatebtn= findViewById(R.id.update);


        //Listener for the update button calling the Updatedata() function
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });


       //back button initialization
        backbtn= findViewById(R.id.bk);


        // the back button will  direct the current interface to the userProfile class
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Edit_Profile.this,userProfile.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        fAuth= FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();
        userID=fAuth.getCurrentUser().getUid();

        DocumentReference documentReference= fstore.collection("users").document(userID);
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if(task.getResult().exists()){
                            String exsistingtName = task.getResult().getString("Name");

                            existingname.setText(exsistingtName);
                        }else {
                            Toast.makeText(getApplicationContext(),"No profile existing",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    //Update method - method to change the current name of the profile
    private void updateProfile() {
        String name = newName.getText().toString();

        final DocumentReference sDoc = db.collection("users").document(userID);

        /** Using the Cloud Firestore client libraries, you can group multiple operations into a single transaction.
         * Transactions are useful when you want to update a field's value based on its current value, or the value of some other field.
         */
        db.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                //DocumentSnapshot snapshot = transaction.get(sfDocRef);

                transaction.update(sDoc, "Name", name );

                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            //User display - successfully updated the new name
            public void onSuccess(Void aVoid) {
                Toast.makeText(Edit_Profile.this, "Updated", Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    //User display - failed updated the new name
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Edit_Profile.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });

    }


}
