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

import java.sql.Ref;
import java.util.ArrayList;

/**
 * Created by CASPER on 12/20/2017.
 */

public class TuesdayFragment extends Fragment {
    private RecyclerView recyclerView;
    private TimeTableListAdapter adapter;
    DatabaseReference mRef;
    FirebaseUser user;

    public TuesdayFragment() {
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
        View view = inflater.inflate(R.layout.fragment_timetable,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_alltimetablefragment);
        if(adapter == null){
            adapter = new TimeTableListAdapter(new ArrayList<TimeTableInfo>());
            recyclerView.setAdapter(adapter);
        }
        else{
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(adapter == null){
            adapter = new TimeTableListAdapter(new ArrayList<TimeTableInfo>());
            recyclerView.setAdapter(adapter);
            mRef.child("coursematerials").child(InstructorsMainPage.courseSelected).child("timetable").child("Tuesday").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    TimeTableInfo info = dataSnapshot.getValue(TimeTableInfo.class);
                    adapter.add(info);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Toast.makeText(getContext(), dataSnapshot.getKey()+" changed", Toast.LENGTH_SHORT).show();
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
        else{
            mRef.child("coursematerials").child(InstructorsMainPage.courseSelected).child("timetable").child("Tuesday").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    TimeTableInfo info = dataSnapshot.getValue(TimeTableInfo.class);
                    adapter.add(info);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Toast.makeText(getContext(), dataSnapshot.getKey()+" changed", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onStop() {
        super.onStop();
        adapter = null;
    }
}
