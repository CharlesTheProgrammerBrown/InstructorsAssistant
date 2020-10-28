package com.example.casper.instructorsassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);
    }

    public void onStudentOpenRegisterationClicked(View view) {
        startActivity(new Intent(ChooseAccountActivity.this,StudentRegistrationActivity.class));
    }

    public void onTeacherOpenRegistrationClicked(View view) {
        startActivity(new Intent(ChooseAccountActivity.this,MainActivity.class));
    }
}
