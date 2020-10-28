package com.example.casper.instructorsassistant;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StudentChatActivity extends AppCompatActivity {
    RecyclerView mRecyclerViewChat;
    ChatRecyclerAdapter mChatRecyclerAdapter;
    EditText messageTextView;
    DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerViewChat = (RecyclerView) findViewById(R.id.recycler_view_student_chatActivity);
        messageTextView=(EditText)findViewById(R.id.msgTextStd);
        mRef= FirebaseDatabase.getInstance().getReference();

        if (mChatRecyclerAdapter == null) {
            mChatRecyclerAdapter = new ChatRecyclerAdapter(new ArrayList<Chat>());
        }
        mRecyclerViewChat.setAdapter(mChatRecyclerAdapter);
        mRef.child("coursematerials").child(AllcoursesFragment.stdCourseSelected).child("chats").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Chat chat = dataSnapshot.getValue(Chat.class);
                mChatRecyclerAdapter.add(chat);
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                String message = messageTextView.getText().toString();
                mRef.child("coursematerials").child(AllcoursesFragment.stdCourseSelected).child("chats").push().setValue(new Chat(StudentMainPage.stdInformation.getName(),"Instructor","ST","IN",System.currentTimeMillis(),message));
            }
        });
    }

}
