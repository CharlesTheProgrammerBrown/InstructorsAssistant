package com.example.casper.instructorsassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCoursePage extends AppCompatActivity {
    EditText coursename;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    FirebaseDatabase database;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_page);

        //Firebase config
        database=FirebaseDatabase.getInstance();
        mRef= database.getReference();
        mAuth=FirebaseAuth.getInstance();
        coursename = (EditText) findViewById(R.id.coursename_text);
    }

    public void onCreateCourseBtnClicked(View view) {
        user= FirebaseAuth.getInstance().getCurrentUser();
        if(TextUtils.isEmpty(coursename.getText().toString().trim())){
            Toast.makeText(this, "Please enter the name of the course", Toast.LENGTH_SHORT).show();
            return;
        }
        mRef.child("courses").child(coursename.getText().toString().toLowerCase()).setValue(new CourseInfo(coursename.getText().toString().trim(),user.getUid()));
        mRef.child("teaches").child(user.getUid()).child(coursename.getText().toString().toLowerCase()).setValue(new CourseInfo(coursename.getText().toString().trim(),user.getUid()));
        mRef.child("coursematerials").child(coursename.getText().toString().toLowerCase()).child("instructor").setValue(user.getUid());
        Toast.makeText(this, coursename.getText().toString()+" is added", Toast.LENGTH_SHORT).show();
        coursename.setText("");
    }
}
