package com.example.casper.instructorsassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QuickNotificationPage extends AppCompatActivity {
    EditText coursenmaeEditText;
    DatabaseReference mRef;
    FirebaseDatabase database;
    FirebaseUser user;
    TextView titleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_notification_page);

        //Firebase config
        database=FirebaseDatabase.getInstance();
        mRef= database.getReference();
        user= FirebaseAuth.getInstance().getCurrentUser();
        coursenmaeEditText=(EditText)findViewById(R.id.notify_text);
        titleText=(TextView) findViewById(R.id.textPageName);
        titleText.setText(InstructorsMainPage.courseSelected.toString().toUpperCase());
    }

    public void onSendNotificationClicked(View view) {
        if(TextUtils.isEmpty(coursenmaeEditText.getText().toString().trim())){
            Toast.makeText(this, "Please enter the notification message", Toast.LENGTH_SHORT).show();
            return;
        }
        String dateString = String.valueOf(System.currentTimeMillis());
        mRef.child("coursematerials").child(InstructorsMainPage.courseSelected).child("quicknotification").child("message").setValue(new QuickMessageInfo(InstructorsMainPage.courseSelected,coursenmaeEditText.getText().toString().trim(),dateString));
        Toast.makeText(this, "Message sent Successfully", Toast.LENGTH_SHORT).show();
    }
}
