package com.example.casper.instructorsassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CourseMaterialsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_materials);
    }

    public void onNotifyBtnClicked(View view) {
     startActivity(new Intent(CourseMaterialsActivity.this,QuickNotificationPage.class));
    }

    public void onReminderClicked(View view) {
        startActivity(new Intent(CourseMaterialsActivity.this,ReminderActivity.class));
    }

    public void onTimeTableClicked(View view) {
        startActivity(new Intent(CourseMaterialsActivity.this,TimeTablePage.class));
    }

    public void onUploadClicked(View view) {
        startActivity(new Intent(CourseMaterialsActivity.this,UploadActivity.class));
    }


    public void onReminderListClicked(View view) {
        startActivity(new Intent(CourseMaterialsActivity.this,InstructorReminderListActivity.class));
    }

    public void onChatBtnClicked(View view) {
        startActivity(new Intent(CourseMaterialsActivity.this,ChatActivity.class));
    }
}
