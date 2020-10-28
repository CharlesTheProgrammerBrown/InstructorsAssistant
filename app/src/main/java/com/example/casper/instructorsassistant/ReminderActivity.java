package com.example.casper.instructorsassistant;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReminderActivity extends AppCompatActivity {
    TextView dateText;
    EditText remindEditTextView;
    DatabaseReference mRef;
    FirebaseDatabase database;

    java.util.Calendar mCalender;
    int year,month,day;
    String dateString;
    long millis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        database=FirebaseDatabase.getInstance();
        mRef= database.getReference();

        dateText = (TextView)findViewById(R.id.dateTextView);
        remindEditTextView= (EditText)  findViewById(R.id.reminderTextView);
        mCalender = java.util.Calendar.getInstance();
        year = mCalender.get(java.util.Calendar.YEAR);
        month = mCalender.get(java.util.Calendar.MONTH);
        day = mCalender.get(java.util.Calendar.DAY_OF_MONTH);
        month+=1;
        dateText.setText(day + "/"+month+"/"+year);

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ReminderActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month+=1;
                        dateText.setText(dayOfMonth+"/"+month+"/"+year);
                        dateString =year+"/"+month+"/"+dayOfMonth+" 14:00:00";
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Date date = null;
                        try {
                            date = sdf.parse(dateString);
                            millis = date.getTime();
                            Toast.makeText(ReminderActivity.this, String.valueOf(millis), Toast.LENGTH_SHORT).show();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
    }

    public void onRemindBtnClicked(View view) {
        if(System.currentTimeMillis() > millis){
            Toast.makeText(this, "Selected date is invalid. Please pick another", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(remindEditTextView.getText())){
            Toast.makeText(this, "Please enter the reminder", Toast.LENGTH_SHORT).show();
            return;
        }

        mRef.child("reminders").child(String.valueOf(millis)).setValue(new ReminderInfo(remindEditTextView.getText().toString().trim(),millis,InstructorsMainPage.courseSelected));
        mRef.child("coursematerials").child(InstructorsMainPage.courseSelected).child("reminders").child(String.valueOf(millis)).setValue(new ReminderInfo(remindEditTextView.getText().toString().trim(),millis,InstructorsMainPage.courseSelected));
        Toast.makeText(this, "Reminder for "+InstructorsMainPage.courseSelected+" has been sent successfully", Toast.LENGTH_SHORT).show();
        remindEditTextView.setText("");
    }
}
