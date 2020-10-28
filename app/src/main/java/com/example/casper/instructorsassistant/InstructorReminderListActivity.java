package com.example.casper.instructorsassistant;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class InstructorReminderListActivity extends AppCompatActivity {
    ReminderListAdapter adapter;
    DatabaseReference mRef;
    FirebaseUser user;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_reminder_list);
        mRef = FirebaseDatabase.getInstance().getReference();
        user= FirebaseAuth.getInstance().getCurrentUser();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_allInstructorsRemindersPage);
        if (adapter == null) {
            adapter = new ReminderListAdapter(new ArrayList<ReminderInfo>(), new OnReminderDeleteListener() {
                @Override
                public void onDleteReminderClicked(ReminderInfo reminderInfo) {
                    Toast.makeText(InstructorReminderListActivity.this, reminderInfo.getMsgInfo()+" is deleted", Toast.LENGTH_SHORT).show();
                    mRef.child("reminders").child(String.valueOf(reminderInfo.getMsgDate())).removeValue();
                    mRef.child("coursematerials").child(InstructorsMainPage.courseSelected).child("reminders").child(String.valueOf(reminderInfo.getMsgDate())).removeValue();
                }
            });

        }
        recyclerView.setAdapter(adapter);
        mRef.child("reminders").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ReminderInfo reminderInfo = dataSnapshot.getValue(ReminderInfo.class);
                if(reminderInfo.getCourseid().equals(InstructorsMainPage.courseSelected))
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Instructor's Reminders");
    }

}
