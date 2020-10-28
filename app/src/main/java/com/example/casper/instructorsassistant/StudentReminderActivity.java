package com.example.casper.instructorsassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StudentReminderActivity extends AppCompatActivity {
    DatabaseReference mRef;
    FirebaseUser user;
    private RecyclerView recyclerView;
    StudentReminderListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_reminder);

        mRef = FirebaseDatabase.getInstance().getReference();
        user= FirebaseAuth.getInstance().getCurrentUser();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_StudentReminderPage);
        if(adapter == null){
            adapter = new StudentReminderListAdapter(new ArrayList<ReminderInfo>());
        }
        recyclerView.setAdapter(adapter);
        mRef.child("coursematerials").child(AllcoursesFragment.stdCourseSelected).child("reminders").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ReminderInfo reminderInfo = dataSnapshot.getValue(ReminderInfo.class);
                Toast.makeText(StudentReminderActivity.this, reminderInfo.getMsgInfo(), Toast.LENGTH_SHORT).show();
                adapter.add(reminderInfo);
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
}
