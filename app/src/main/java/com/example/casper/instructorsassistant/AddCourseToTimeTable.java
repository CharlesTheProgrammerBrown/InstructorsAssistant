package com.example.casper.instructorsassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCourseToTimeTable extends AppCompatActivity {
    Spinner spinner;
    Spinner classTypeSpinner;
    Spinner daysSpinner;
    String selectedTime;
    String selectedClassType;
    String selectedDay;
    String ordered;
    EditText chooseClassEditText;
    DatabaseReference mRef;
    FirebaseDatabase database;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_to_time_table);
        spinner=(Spinner) findViewById(R.id.timePeriodSpinner);
        classTypeSpinner=(Spinner) findViewById(R.id.classTypeSpinner);
        daysSpinner=(Spinner) findViewById(R.id.daySpinner);
        chooseClassEditText=(EditText) findViewById(R.id.chooseClassEditText);

        database=FirebaseDatabase.getInstance();
        mRef= database.getReference();


        //Add course Time arrayadapter
        ArrayAdapter<String> myAdaper = new ArrayAdapter<String>(AddCourseToTimeTable.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.timeperiod_array));
        myAdaper.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdaper);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTime = parent.getItemAtPosition(position).toString();
                int order = position;
                ordered = String.valueOf(order);
                Toast.makeText(AddCourseToTimeTable.this, selectedTime, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Add Class type arrayadapter
        ArrayAdapter<String> myTypeAdapter = new ArrayAdapter<String>(AddCourseToTimeTable.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.classtype_array));
        myTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classTypeSpinner.setAdapter(myTypeAdapter);
        classTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedClassType = parent.getItemAtPosition(position).toString();
                Toast.makeText(AddCourseToTimeTable.this, selectedClassType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Add Days Spinner
        ArrayAdapter<String> daysAdapter = new ArrayAdapter<String>(AddCourseToTimeTable.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.days_array));
        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daysSpinner.setAdapter(daysAdapter);
        daysSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDay = parent.getItemAtPosition(position).toString();
                Toast.makeText(AddCourseToTimeTable.this, selectedDay, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void onSubmitTableClicked(View view) {
        if(TextUtils.isEmpty(chooseClassEditText.getText().toString().trim())){
            Toast.makeText(this, "Please enter Class Venue", Toast.LENGTH_SHORT).show();
        }
        String classname = chooseClassEditText.getText().toString().trim();
        mRef.child("coursematerials").child(InstructorsMainPage.courseSelected).child("timetable").child(selectedDay).child(ordered).setValue(new TimeTableInfo(InstructorsMainPage.courseSelected,selectedTime,selectedClassType,classname));
        Toast.makeText(this, "Timetable change for "+selectedDay+" at "+selectedTime, Toast.LENGTH_SHORT).show();
    }

    public void onRemoveClicked(View view) {
        mRef.child("coursematerials").child(InstructorsMainPage.courseSelected).child("timetable").child(selectedDay).child(ordered).removeValue();
        Toast.makeText(this, "The lecture at "+selectedTime+" on "+selectedDay, Toast.LENGTH_SHORT).show();
    }
}
