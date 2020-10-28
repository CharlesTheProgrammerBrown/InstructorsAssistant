package com.example.casper.instructorsassistant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class StudentRegistrationActivity extends AppCompatActivity {
    EditText nameText;
    EditText idText;
    EditText emailText;
    EditText passwordText;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    FirebaseDatabase database;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        //Elements config
        nameText=(EditText)findViewById(R.id.fullname_text);
        idText=(EditText)findViewById(R.id.stdId_text);
        emailText=(EditText)findViewById(R.id.std_email_text);
        passwordText=(EditText)findViewById(R.id.std_password_text);

        //Firebase config
        database=FirebaseDatabase.getInstance();
        mRef= database.getReference();
        mAuth=FirebaseAuth.getInstance();
    }

    public void onStudentRegisterBtnClicked(View view) {
        String email=emailText.getText().toString().trim();
        String password=passwordText.getText().toString().trim();
        String name=nameText.getText().toString().trim();
        String id=idText.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(name)){
            Toast.makeText(getApplicationContext(),"Please enter name",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(id)){
            Toast.makeText(getApplicationContext(),"Please enter Student Id",Toast.LENGTH_SHORT).show();
            return;
        }
        registerUser(name,email,id,password);
    }

    private void registerUser(final String name, final String email, final String id, final String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(StudentRegistrationActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    user= FirebaseAuth.getInstance().getCurrentUser();
                    mRef.child("registeredUsers").child(user.getUid()).setValue(new UserCategory("student", FirebaseInstanceId.getInstance().getToken()));
                    mRef.child("users").child(user.getUid()).setValue(new StudentInfo(name,id,email,password,FirebaseInstanceId.getInstance().getToken(),user.getUid()));
                    Toast.makeText(StudentRegistrationActivity.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                    mAuth.signOut();
                    startActivity(new Intent(StudentRegistrationActivity.this,LoginActivity.class));
                    finish();
                }
                else {
                    FirebaseAuthException e = (FirebaseAuthException)task.getException();
                    Log.e("RegisterActivity", "Failed Registration", e);
                    Toast.makeText(getApplicationContext(), "Registration failed......"+e.getMessage().toString() , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
