package com.example.c195pa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.c195pa.Entities.Assessment;
import com.example.c195pa.Entities.Course;
import com.example.c195pa.View.AssessmentViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditAssessmentActivity extends AppCompatActivity {

    private EditText mTitle;
    private EditText mDueDate;
    int assessmentId;
    int courseId;
    AssessmentViewModel mAssessmentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAssessmentViewModel = ViewModelProviders.of(this).get(AssessmentViewModel.class);

        final Button button = findViewById(R.id.button_save);
        //Write code to save course info, but not return to the next screen.
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mTitle.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    Date due = null;
                    try {
                        due = dateFormat.parse(mDueDate.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String title = mTitle.getText().toString();
                    Spinner spinner = findViewById(R.id.spinner);
                    String type = spinner.getSelectedItem().toString();

                    Assessment assessment = new Assessment(assessmentId, title, due, type, courseId);

                    replyIntent.putExtra("assessment", assessment);
                    setResult(RESULT_CANCELED, replyIntent);
                }
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        mTitle = findViewById(R.id.title_text);
        mDueDate = findViewById(R.id.due_date_text);

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner);
        String[] items = new String[]{"Objective", "Performance"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            Intent i = getIntent();
            String title = i.getStringExtra("name");
            String dueDate = i.getStringExtra("dueDate");
            String spinnerChoice = i.getStringExtra("type");
            mTitle.setText(title, TextView.BufferType.EDITABLE);
            mDueDate.setText(dueDate, TextView.BufferType.EDITABLE);
            courseId = i.getIntExtra("courseId", 0);
            assessmentId = i.getIntExtra("assessmentId", 0);

            //This is not quite working
            if (spinnerChoice == "Objective") {
                dropdown.setSelection(0);
            } else if (spinnerChoice == "Performance"){
                dropdown.setSelection(1);
            } else {
                Log.d("check", "Nothing");
            }
        }
    }

    public void DeleteAssessment(View view) {
        mAssessmentViewModel.deleteAssessment(assessmentId);
        Intent intent = new Intent();
        setResult(RESULT_FIRST_USER, intent);
        finish();
    }
}


