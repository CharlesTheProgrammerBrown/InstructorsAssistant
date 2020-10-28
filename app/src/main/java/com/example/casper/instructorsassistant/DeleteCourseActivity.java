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

public class DeleteCourseActivity extends AppCompatActivity {
    EditText coursename;
    DatabaseReference mRef;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_course);
        coursename = (EditText) findViewById(R.id.instructor_coursename_editText);

        mRef = FirebaseDatabase.getInstance().getReference();
        user= FirebaseAuth.getInstance().getCurrentUser();
    }

    public void onInstructorDeleteCourseClicked(View view) {
        if(TextUtils.isEmpty(coursename.getText().toString().trim())){
            Toast.makeText(this, "Please enter course name to delete", Toast.LENGTH_SHORT).show();
            return;
        }
        mRef.child("teaches").child(user.getUid()).child(coursename.getText().toString().trim()).removeValue();
        mRef.child("coursematerials").child(coursename.getText().toString().trim()).removeValue();
        mRef.child("courses").child(coursename.getText().toString().trim()).removeValue();
    }
}
