package com.Shredded.finalProject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import info.Shredded.finalProject.R;

public class DisplayExercisesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_exercises);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            if(bundle.getString("some") != null)
            {
                Toast.makeText(getApplicationContext(), "data:" + bundle.getString("some"), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
