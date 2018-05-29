package com.Shredded.finalProject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import info.Shredded.finalProject.R;
import com.Shredded.finalProject.activity.DisplayExercisesActivity;


public class ExercisesFragment extends Fragment {

    ImageButton imageButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photos, container, false);

        ImageButton imageButton = view.findViewById(R.id.pack_btn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),DisplayExercisesActivity.class);
                i.putExtra("Some", "some data");
                startActivity(i);
            }
        });

        return view;
    }

}
