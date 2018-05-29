package com.Shredded.finalProject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.Shredded.finalProject.Daily_Training;
import com.Shredded.finalProject.Utils.Calendar;

import info.Shredded.finalProject.R;
import com.Shredded.finalProject.SettingPage;
import com.Shredded.finalProject.activity.ListWarmup;


public class WarmUpFragment extends Fragment {

    Button btnExercise, btnSetting, btnCalendar;
    ImageView btnTraining;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

            btnExercise = view.findViewById(R.id.btnExercises);
            btnSetting = view.findViewById(R.id.btnSetting);
            btnTraining = view.findViewById(R.id.btnTraining);
            btnCalendar = view.findViewById(R.id.btnCalendar);

            btnTraining.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Daily_Training.class);
                    startActivity(intent);
                }
            });

            btnCalendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Calendar.class);
                    startActivity(intent);
                }
            });

            btnSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), SettingPage.class);
                    startActivity(intent);
                }
            });

            btnExercise.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ListWarmup.class);
                    startActivity(intent);
                }
            });

        return view;
    }

}
