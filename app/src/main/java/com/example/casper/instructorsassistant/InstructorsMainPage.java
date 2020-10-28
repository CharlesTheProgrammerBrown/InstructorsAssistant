package com.example.casper.instructorsassistant;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class InstructorsMainPage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CourseListAdapter adapter;
    DatabaseReference mRef;
    FirebaseUser user;
    public static String courseSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructors_main_page);
        mRef = FirebaseDatabase.getInstance().getReference();
        user= FirebaseAuth.getInstance().getCurrentUser();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_instructorpage);
        if(adapter == null){
            adapter = new CourseListAdapter(new ArrayList<CourseInfo>(), new onItemViewClickListener() {
                @Override
                public void onItemCourseClicked(String coursename) {
                    courseSelected = coursename;
                    startActivity(new Intent(InstructorsMainPage.this,CourseMaterialsActivity.class));
                }
            });
            recyclerView.setAdapter(adapter);
        }
        mRef.child("teaches").child(user.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CourseInfo courseInfo = dataSnapshot.getValue(CourseInfo.class);
                adapter.add(courseInfo);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onFloatBtnClicked(View view) {
        startActivity(new Intent(InstructorsMainPage.this,AddCoursePage.class));
    }

    public void onDeleteBtnClicked(View view) {
        startActivity(new Intent(InstructorsMainPage.this,DeleteCourseActivity.class));
    }
}
