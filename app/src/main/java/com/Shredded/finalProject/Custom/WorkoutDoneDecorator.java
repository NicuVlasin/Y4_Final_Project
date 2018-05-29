package com.Shredded.finalProject.Custom;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.HashSet;

/**
 * Created by nvlas on 10/04/2018.
 */

public class WorkoutDoneDecorator implements DayViewDecorator{

    HashSet<CalendarDay> list;
    ColorDrawable colorDrawable;

    public WorkoutDoneDecorator(HashSet<CalendarDay> list)
    {
        this.list = list;
        colorDrawable = new ColorDrawable(Color.parseColor("#333639"));

    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return list.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {

        view.setBackgroundDrawable(colorDrawable);
    }
}
