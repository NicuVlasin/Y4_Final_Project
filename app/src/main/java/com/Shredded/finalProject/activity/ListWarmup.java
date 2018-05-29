package com.Shredded.finalProject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.Shredded.finalProject.Adapter.RecycleViewAdapter;
import com.Shredded.finalProject.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

import info.Shredded.finalProject.R;

public class ListWarmup extends AppCompatActivity {

    List<Exercise> exerciseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_warmup);


        initData();

        recyclerView = findViewById(R.id.list_ex);
        adapter = new RecycleViewAdapter(exerciseList, getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {

        exerciseList.add(new Exercise(R.drawable.easy_pose, "Easy Pose"));
        exerciseList.add(new Exercise(R.drawable.cobra_pose, "Cobra Pose"));
        exerciseList.add(new Exercise(R.drawable.downward_facing_dog, "Downward_facing_dog Pose"));
        exerciseList.add(new Exercise(R.drawable.boat_pose, "Boat Pose"));
        exerciseList.add(new Exercise(R.drawable.half_pigeon, "Half_pigeon Pose"));
        exerciseList.add(new Exercise(R.drawable.low_lunge, "Low Lunge Pose"));
        exerciseList.add(new Exercise(R.drawable.upward_bow, "Upward Bow Pose"));
        exerciseList.add(new Exercise(R.drawable.crescent_lunge, "Crescent_lunge Pose"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose, "Warrior Pose"));
        exerciseList.add(new Exercise(R.drawable.bow_pose, "Bow Pose"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose_2, "Warrior Pose No.2"));
    }
}
