package com.example.casper.instructorsassistant;

import android.content.Intent;
import android.net.Uri;
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

public class StudentUploadActivity extends AppCompatActivity {
    DatabaseReference mRef;
    FirebaseUser user;
    private RecyclerView recyclerView;
    StudentUploadListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_upload);

        mRef = FirebaseDatabase.getInstance().getReference();
        user= FirebaseAuth.getInstance().getCurrentUser();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_StudentUploadPage);
        if(adapter == null){
            adapter = new StudentUploadListAdapter(new ArrayList<UploadInfo>(), new onMyDownloadUploadListener() {
                @Override
                public void onUploadDownloadClicked(UploadInfo uploadInfo) {
                    Toast.makeText(StudentUploadActivity.this, uploadInfo.getName()+" is downloading", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(uploadInfo.getPath()));
                    startActivity(intent);
                }
            });
        }
        recyclerView.setAdapter(adapter);
        mRef.child("coursematerials").child(AllcoursesFragment.stdCourseSelected).child("uploads").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UploadInfo uploadInfo = dataSnapshot.getValue(UploadInfo.class);
                adapter.add(uploadInfo);
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
