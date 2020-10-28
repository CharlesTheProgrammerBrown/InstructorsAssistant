package com.example.casper.instructorsassistant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "Firebase_Auth_Info";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;
    private EditText emailText;
    private EditText passwordText;
    private Button logBtn;
    DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupFirebaseAuth();
        mRef= FirebaseDatabase.getInstance().getReference();
        emailText=(EditText)findViewById(R.id.email_text);
        passwordText=(EditText)findViewById(R.id.password_text);
    }

    public void onCreateAccountClick(View view) {
        startActivity(new Intent(LoginActivity.this,ChooseAccountActivity.class));
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void setupFirebaseAuth(){
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(LoginActivity.this, "Signed_in", Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(LoginActivity.this, "Signed_out", Toast.LENGTH_SHORT).show();
                }
                // ...
            }
        };
    }

    public void onLoginBtnClicked(View view) {
        String email=emailText.getText().toString().trim();
        String password=passwordText.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }

        loginAuthenticate(email,password);
    }

    private void loginAuthenticate(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    user =FirebaseAuth.getInstance().getCurrentUser();
                    //Toast.makeText(LoginActivity.this, "I am Here", Toast.LENGTH_SHORT).show();
                    checkUser(user);
                }
                else {
                    FirebaseAuthException e = (FirebaseAuthException)task.getException();
                    Log.e("LoginActivity", "Login Failed", e);
                    Toast.makeText(getApplicationContext(), "Login failed...... "+e.getMessage().toString() , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkUser(FirebaseUser user) {
        mRef.child("registeredUsers").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
                UserCategory info= (UserCategory)dataSnapshot.getValue(UserCategory.class);
                if(info.category.matches("student")){
                    Toast.makeText(LoginActivity.this, "Student has been logged in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,StudentMainPage.class));

                }
                else{
                    if(user.isEmailVerified()){
                        Toast.makeText(LoginActivity.this, "User is verified", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,InstructorsMainPage.class));
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "User is not verified", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
