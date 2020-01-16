package com.example.c195pa;

import android.content.Intent;
import android.os.Bundle;

import com.example.c195pa.Adapters.AssessmentAdapter;
import com.example.c195pa.Adapters.CourseAdapter;
import com.example.c195pa.Entities.Assessment;
import com.example.c195pa.Entities.Course;
import com.example.c195pa.View.AssessmentViewModel;
import com.example.c195pa.View.TermViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AssessmentActivity extends AppCompatActivity implements AssessmentAdapter.OnAssessmentListener {

    private AssessmentViewModel mAssessmentViewModel;
    public static final int NEW_ASSESSMENT_ACTIVITY_REQUEST_CODE = 1;
    int courseId;
    AssessmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Assessments");


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            courseId = extras.getInt("courseId");
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(view.getContext(), AddAssessmentActivity.class), NEW_ASSESSMENT_ACTIVITY_REQUEST_CODE);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview_assessment);
        adapter = new AssessmentAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAssessmentViewModel = ViewModelProviders.of(this).get(AssessmentViewModel.class);
        mAssessmentViewModel.getAssessmentsByCourseId(courseId).observe(this, new Observer<List<Assessment>>() {
            @Override
            public void onChanged(List<Assessment> assessments) {
                adapter.setAssessments(assessments);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_ASSESSMENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date dueDate = null;
            try {
                dueDate = dateFormat.parse(data.getStringExtra(AddAssessmentActivity.EXTRA_REPLY2));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String title = data.getStringExtra(AddAssessmentActivity.EXTRA_REPLY);
            String type = data.getStringExtra(AddAssessmentActivity.EXTRA_REPLY3);

            Assessment assessment = new Assessment(title, dueDate, type, courseId);
            mAssessmentViewModel.insertAssessment(assessment);

        } else if (requestCode == NEW_ASSESSMENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_CANCELED) {
            Assessment assessment = (Assessment) data.getSerializableExtra("assessment");
            Log.d("check this", assessment.getName());
            mAssessmentViewModel.updateAssessment(assessment);
        } else if (requestCode == NEW_ASSESSMENT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_FIRST_USER){
            Toast.makeText(getApplicationContext(),
                    "Assessment Deleted",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onAssessmentClick(int position) {

        final RecyclerView recyclerView = findViewById(R.id.recyclerview_assessment);
        String title1 = ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.textView)).getText().toString();
        Toast.makeText(getApplicationContext(), title1, Toast.LENGTH_SHORT).show();
        final Intent intent = new  Intent(this, EditAssessmentActivity.class);

        SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Assessment assessment = adapter.getItem(position);
        String name = assessment.getName();
        Date date = assessment.getDueDate();
        String dueDate = sDF.format(date);
        String type = assessment.getType();
        int assessmentId = assessment.getId();
        int courseId = assessment.getCourseId();

        intent.putExtra("name", name);
        intent.putExtra("type", type);
        intent.putExtra("dueDate", dueDate);
        intent.putExtra("assessmentId", assessmentId);
        intent.putExtra("courseId", courseId);
        startActivityForResult(intent, NEW_ASSESSMENT_ACTIVITY_REQUEST_CODE);

    }

}
