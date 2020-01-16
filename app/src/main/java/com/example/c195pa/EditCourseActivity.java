package com.example.c195pa;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.example.c195pa.Entities.Course;
import com.example.c195pa.View.CourseViewModel;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditCourseActivity extends AppCompatActivity {

    public static final int NEW_COURSE_ACTIVITY_REQUEST_CODE = 1;
    private EditText mCourseName;
    private EditText mStartDate;
    private EditText mEndDate;
    private EditText mStatus;
    private EditText mMentorName;
    private EditText mMentorPhone;
    private EditText mMentorEmail;
    private EditText mNotes;
    int termId;
    int courseId;
    //Global notes String due to needing the ability to access it from the sharing method
    String notes;
    CourseViewModel mCourseViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Course");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        mCourseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);



        final Button button = findViewById(R.id.button2);
        //Write code to save course info, but not return to the next screen.
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mCourseName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    Date start = null;
                    Date end= null;
                    try {
                        start = dateFormat.parse(mStartDate.getText().toString());
                        end= dateFormat.parse(mEndDate.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String name = mCourseName.getText().toString();
                    String status = mStatus.getText().toString();
                    String mentorName = mMentorName.getText().toString();
                    String mentorPhone = mMentorPhone.getText().toString();
                    String mentorEmail = mMentorEmail.getText().toString();
                    String notes = mNotes.getText().toString();

                    Course course = new Course(courseId, name, start, end, status, mentorName, mentorEmail, mentorPhone, termId, notes);

                    replyIntent.putExtra("course", course);
                    setResult(RESULT_CANCELED, replyIntent);
                }
                finish();
            }
        });

        Button fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AssessmentActivity.class);
                intent.putExtra("courseId", courseId);
                startActivityForResult(intent, NEW_COURSE_ACTIVITY_REQUEST_CODE);
            }
        });
        //Change date strings to date type once working properly

    }

    @Override
    protected void onResume() {
        super.onResume();

        mCourseName = findViewById(R.id.courseNameEdit);
        mStartDate = findViewById(R.id.startDateEdit);
        mEndDate = findViewById(R.id.endDatEdit);
        mStatus = findViewById(R.id.statusEdit);
        mMentorName = findViewById(R.id.mentorNameEdit);
        mMentorPhone = findViewById(R.id.mentorPhoneEdit);
        mMentorEmail = findViewById(R.id.mentorEmailEdit);
        mNotes = findViewById(R.id.noteEdit);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            Intent i = getIntent();
            String name = i.getStringExtra("name");
            String start = i.getStringExtra("start");
            String end = i.getStringExtra("end");
            String status = i.getStringExtra("status");
            String mentor = i.getStringExtra("mentor");
            String mentorEmail = i.getStringExtra("mentorEmail");
            String mentorPhone = i.getStringExtra("mentorPhone");
            notes = i.getStringExtra("notes");
            mCourseName.setText(name, TextView.BufferType.EDITABLE);
            mStartDate.setText(start, TextView.BufferType.EDITABLE);
            mEndDate.setText(end, TextView.BufferType.EDITABLE);
            mStatus.setText(status, TextView.BufferType.EDITABLE);
            mMentorName.setText(mentor, TextView.BufferType.EDITABLE);
            mMentorPhone.setText(mentorPhone, TextView.BufferType.EDITABLE);
            mMentorEmail.setText(mentorEmail, TextView.BufferType.EDITABLE);
            mNotes.setText(notes, TextView.BufferType.EDITABLE);
            termId = i.getIntExtra("termId", 0);
            courseId = i.getIntExtra("courseId", 0);

        }
    }

    public void DeleteCourse(View view) {
        mCourseViewModel.deleteCourse(courseId);
        Intent replyIntent = new Intent();
        setResult(RESULT_FIRST_USER, replyIntent);
        finish();
        //Intent intent = new Intent(this, CourseActivity.class);
       // startActivity(intent);
    }

    public void shareNotes(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, notes);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    // public void saveCourse(View view) {



}
