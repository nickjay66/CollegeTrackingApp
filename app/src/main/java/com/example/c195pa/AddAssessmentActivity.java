package com.example.c195pa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddAssessmentActivity extends AppCompatActivity {

    private EditText mTitle;
    private EditText mDueDate;
    private String dueDate;
    public static final String EXTRA_REPLY =
            "com.example.android.roomtermsssample.REPLY";
    public static final String EXTRA_REPLY2 =
            "com.example.android.roomtermsssample.REPLY2";
    public static final String EXTRA_REPLY3 =
            "com.example.android.roomtermsssample.REPLY3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner);
        String[] items = new String[]{"Objective", "Performance"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        mTitle = findViewById(R.id.title_text);
        mDueDate = findViewById(R.id.due_date_text);

        final Button button = findViewById(R.id.button_save);
        //Write code to save course info, but not return to the next screen.
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mTitle.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    Spinner spinner = findViewById(R.id.spinner);
                    String type = spinner.getSelectedItem().toString();
                    String title = mTitle.getText().toString();
                    dueDate = mDueDate.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, title);
                    replyIntent.putExtra(EXTRA_REPLY2, dueDate);
                    replyIntent.putExtra(EXTRA_REPLY3, type);

                    setResult(RESULT_OK, replyIntent);
                }

                //Add more switch statements to throw alert messages
                if (dueDate.contains("/")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(AddAssessmentActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Please format date yyyy-MM-dd");

                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                        }
                    });

                    alertDialog.show();
                    //Toast.makeText(getApplicationContext(), "Please format date mm-dd-yyyy", Toast.LENGTH_LONG).show();
                } else {
                    finish();
                }
            }
        });


    }
}
