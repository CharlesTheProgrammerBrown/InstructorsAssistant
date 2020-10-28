package com.example.casper.instructorsassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StudentCourseMaterialPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course_material_page);
    }

    public void onStdReminderClicked(View view) {
        startActivity(new Intent(StudentCourseMaterialPage.this,StudentReminderActivity.class));
    }

    public void onStdUploadClicked(View view) {
        startActivity(new Intent(StudentCourseMaterialPage.this,StudentUploadActivity.class));
    }

    public void onStdTimetableClicked(View view) {
        startActivity(new Intent(StudentCourseMaterialPage.this,StudentTimeTableActivity.class));
    }

    public void onChatRoomClicked(View view) {
        startActivity(new Intent(StudentCourseMaterialPage.this,StudentChatActivity.class));
    }

    public void onStdNotificationClicked(View view) {
        startActivity(new Intent(StudentCourseMaterialPage.this,StudentNotificationActivity.class));
    }
}
