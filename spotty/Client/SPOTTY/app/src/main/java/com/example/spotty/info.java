package com.example.spotty;


//This class is used to store all the details of the user who gets logged in as to make a use of retrieving it with user profile class to display
public class info {
    String UserName;

    String Email;
    String Password;


    public info(String UserName, String Email, String Password) {
        this.UserName = UserName;
        this.Email= Email;
        this.Password= Password;

    }

    public String getName() {
        return UserName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

//    public void signup (){
//
//        mAuth = FirebaseAuth.getInstance();
//
//        //create new user
//        mAuth.createUserWithEmailAndPassword(Email, Password)
//                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            System.out.println("createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            System.out.println("createUserWithEmail:failure"+ task.getException());
////                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
////                                    Toast.LENGTH_SHORT).show();
////                            updateUI(null);
//                        }
//                    }
//                });
//
//    }





}

