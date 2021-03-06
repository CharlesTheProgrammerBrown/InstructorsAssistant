package com.example.casper.instructorsassistant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by CASPER on 12/5/2017.
 */

public class MyCoursesFragment extends Fragment {
    private RecyclerView recyclerView;
    private CourseAddAdapter adapter;
    DatabaseReference mRef;
    FirebaseUser user;
    public MyCoursesFragment() {
        //Required empty constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRef = FirebaseDatabase.getInstance().getReference();
        user= FirebaseAuth.getInstance().getCurrentUser();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_allcourses,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_allcoursesfragment);
        if(adapter == null){
            adapter = new CourseAddAdapter(new ArrayList<CourseInfo>(), getContext(), new onMyAddCourseListener() {
                @Override
                public void addBtnClicked(CourseInfo course) {
                    mRef.child("takes").child(user.getUid()).child(course.getCoursename()).setValue(course);
                    mRef.child("coursematerials").child(course.getCoursename().toLowerCase()).child("students").child(user.getUid()).setValue(user.getUid());
                    Toast.makeText(getContext(), course.getCoursename()+" is added", Toast.LENGTH_SHORT).show();
                }
            });
            recyclerView.setAdapter(adapter);
        }
        mRef.child("courses").addChildEventListener(new ChildEventListener() {
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
        return view;
    }
}
