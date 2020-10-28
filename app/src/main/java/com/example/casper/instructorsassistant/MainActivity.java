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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Register_Log";
    EditText instEmailEditText;
    EditText instPassEditText;
    EditText instTokenEditText;
    Button getTokenBtn;
    LinearLayout second;
    DatabaseReference mRef;
    String emailTyped ="";
    String instId="";
    private FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Firebase config
        mAuth = FirebaseAuth.getInstance();
        mRef= FirebaseDatabase.getInstance().getReference();
        if(FirebaseInstanceId.getInstance().getToken()==null) {
            String token = FirebaseInstanceId.getInstance().getToken();
            Toast.makeText(this, token, Toast.LENGTH_SHORT).show();
        }
        else{
            mRef.child("fcmToken").setValue(FirebaseInstanceId.getInstance().getToken());
        }
        //Layout config
        instEmailEditText =(EditText)findViewById(R.id.instructorEmailEditTextview);
        instPassEditText=(EditText)findViewById(R.id.PasswordTextView);
        instTokenEditText=(EditText)findViewById(R.id.tokenTextView);
        getTokenBtn=(Button)findViewById(R.id.instructorGetBtn);
        second=(LinearLayout)findViewById(R.id.secondLayout);

        getTokenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if(TextUtils.isEmpty(instEmailEditText.getText())){
                     Toast.makeText(MainActivity.this, "Please enter Email address", Toast.LENGTH_SHORT).show();
                     return;
                 }

                 if(TextUtils.isEmpty(instTokenEditText.getText())){
                     Toast.makeText(MainActivity.this, "Please enter Token", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 mRef.child("InstructorsToken").addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(DataSnapshot dataSnapshot) {
                         for(DataSnapshot data:dataSnapshot.getChildren())
                         {
                             InstructorRegInfo info = data.getValue(InstructorRegInfo.class);
                             Toast.makeText(MainActivity.this, info.instEmail.toString(), Toast.LENGTH_SHORT).show();
                             if(info.instEmail.toString().matches(instEmailEditText.getText().toString()) && info.instToken.matches(instTokenEditText.getText().toString()))
                             {
                                 emailTyped = info.instEmail.toString();
                                 instId = info.instId.toString();
                                 Toast.makeText(MainActivity.this, "Please provide your Password", Toast.LENGTH_SHORT).show();
                                 second.setVisibility(LinearLayout.VISIBLE);
                                 return;
                             }
                         }
                         Toast.makeText(MainActivity.this, "Go to Student Login Page", Toast.LENGTH_SHORT).show();
                     }

                     @Override
                     public void onCancelled(DatabaseError databaseError) {

                     }
                 });
            }
        });
    }

    public void onRegisterBtnClicked(View view) {
        if(TextUtils.isEmpty(instPassEditText.getText().toString())){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        final String email=emailTyped;
        final String password=instPassEditText.getText().toString();
        final String instID=instId;
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                        if(task.isSuccessful()){
                            user= FirebaseAuth.getInstance().getCurrentUser();
                            mRef.child("registeredUsers").child(user.getUid()).setValue(new UserCategory("instructor", FirebaseInstanceId.getInstance().getToken()));
                            mRef.child("instructors").child(user.getUid()).setValue(new InstructorInfo(email,user.getUid(),FirebaseInstanceId.getInstance().getToken(),instID));
                            sendVerificationMail(user);
                            mAuth.signOut();
                            startActivity(new Intent(MainActivity.this,LoginActivity.class));
                            finish();
                        }
                        else{
                            Toast.makeText(MainActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void sendVerificationMail(final FirebaseUser user) {
        user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Verification Email sent to "+user.getEmail(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d(TAG,"SendEmailVerification",task.getException());
                    Toast.makeText(MainActivity.this, "Failed to send verification email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
