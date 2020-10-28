package com.example.casper.instructorsassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;

public class StudentNotificationActivity extends AppCompatActivity {
    TextView msgText;
    DatabaseReference mRef;
    FirebaseDatabase database;
    FirebaseUser user;
    TextView titleText;
    TextView dateText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_notification);

        //Firebase config
        database=FirebaseDatabase.getInstance();
        mRef= database.getReference();
        user= FirebaseAuth.getInstance().getCurrentUser();

        msgText=(TextView) findViewById(R.id.stdNotifyText);
        titleText=(TextView) findViewById(R.id.stdNotifyHeader_text);
        dateText =(TextView) findViewById(R.id.stdNotifyDate);

        mRef.child("coursematerials").child(AllcoursesFragment.stdCourseSelected).child("quicknotification").child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                  QuickMessageInfo quickMessageInfo = dataSnapshot.getValue(QuickMessageInfo.class);
                Toast.makeText(StudentNotificationActivity.this, quickMessageInfo.getMsgContent(), Toast.LENGTH_SHORT).show();
                  msgText.setText(quickMessageInfo.getMsgContent());
                  titleText.setText(quickMessageInfo.getMsgHeader());
                  Long date = Long.valueOf(quickMessageInfo.getMsgDate());
                  Date dates = new Date(date);
                  DateFormat formatter = DateFormat.getDateInstance();
                  dateText.setText(formatter.format(dates));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
