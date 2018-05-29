package com.Shredded.finalProject.Utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.Shredded.finalProject.Custom.WorkoutDoneDecorator;
import com.Shredded.finalProject.Database.WarmUpDB;
import info.Shredded.finalProject.R;

public class Calendar extends AppCompatActivity {

    MaterialCalendarView materialCalendarView;
    HashSet<CalendarDay> list = new HashSet<>();

    WarmUpDB warmUpDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        warmUpDB = new WarmUpDB(this);
        materialCalendarView = findViewById(R.id.calendar);
        List<String> workoutDay = warmUpDB.getWorkoutDays();
        HashSet<CalendarDay> convertedList = new HashSet<>();
        for (String value:workoutDay)
        {
            convertedList.add(CalendarDay.from(new Date(Long.parseLong(value))));
        }
        materialCalendarView.addDecorator(new WorkoutDoneDecorator(convertedList));


    }
}
