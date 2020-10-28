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

public class StudentRemoveCourseActivity extends AppCompatActivity {
    EditText coursename;
    DatabaseReference mRef;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_remove_course);

        mRef = FirebaseDatabase.getInstance().getReference();
        user= FirebaseAuth.getInstance().getCurrentUser();
        coursename = (EditText) findViewById(R.id.std_coursename_editText);
    }

    public void onDeleteCourseClicked(View view) {
        if(TextUtils.isEmpty(coursename.getText().toString().trim())){
            Toast.makeText(this, "Please enter the name of the course", Toast.LENGTH_SHORT).show();
            return;
        }
        mRef.child("takes").child(user.getUid()).child(coursename.getText().toString().toLowerCase().trim()).removeValue();
        mRef.child("coursematerials").child(coursename.getText().toString().toLowerCase().trim()).child("students").child(user.getUid()).removeValue();
        Toast.makeText(this, "Course deleted for this user", Toast.LENGTH_SHORT).show();
    }
}
