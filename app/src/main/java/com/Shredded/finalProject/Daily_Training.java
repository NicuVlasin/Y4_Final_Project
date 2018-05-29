package com.Shredded.finalProject;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.Shredded.finalProject.Model.Exercise;
import com.Shredded.finalProject.Utils.Common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.Shredded.finalProject.Database.WarmUpDB;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class Daily_Training extends AppCompatActivity {

    Button btnStart;
    ImageView ex_image;
    TextView txtGetReady, txtCountdown, txtxTimer,ex_name;
    ProgressBar progressBar;
    LinearLayout layoutGetready;

    int ex_id = 0, limit_time = 0;

    List<Exercise> list = new ArrayList<>();

    WarmUpDB warmUpDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily__training);

        initData();

        warmUpDB = new WarmUpDB(this);




        btnStart = findViewById(R.id.btnStart);

        ex_image = findViewById(R.id.detail_image);

        txtCountdown = findViewById(R.id.txtCountdown);
        txtGetReady = findViewById(R.id.txtGetReady);
        txtxTimer = findViewById(R.id.timer);
        ex_name = findViewById(R.id.title);

        layoutGetready = findViewById(R.id.layout_get_ready);

        progressBar = (MaterialProgressBar)findViewById(R.id.progressBar);

        //Set Data
        progressBar.setMax(list.size());

        setWarmUpInformation(ex_id);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnStart.getText().toString().toLowerCase().equals("start"))
                {
                    showGetReady();
                    btnStart.setText("done");
                }
                else if (btnStart.getText().toString().toLowerCase().equals("done"))
                {
                    if (warmUpDB.getSettingMode() == 0)
                        exercisesEasyCountDown.cancel();
                    else if (warmUpDB.getSettingMode() == 1)
                        exercisesMediumCountDown.cancel();
                    else if (warmUpDB.getSettingMode() == 2)
                        exercisesHardCountDown.cancel();

                    restCountDown.cancel();

                    if (ex_id < list.size())
                    {
                        showRestTime();
                        ex_id++;
                        progressBar.setProgress(ex_id);
                        txtxTimer.setText("");
                    }
                    else
                    {
                        showFinished();
                    }
                }
                else
                {
                    if (warmUpDB.getSettingMode() == 0)
                        exercisesEasyCountDown.cancel();
                    else if (warmUpDB.getSettingMode() == 1)
                        exercisesMediumCountDown.cancel();
                    else if (warmUpDB.getSettingMode() == 2)
                        exercisesHardCountDown.cancel();

                    restCountDown.cancel();

                    if (ex_id < list.size())
                    {
                        setWarmUpInformation(ex_id);
                    }
                    else
                    {
                       showFinished();
                    }
                }
            }
        });
    }

    private void showRestTime() {
        ex_image.setVisibility(View.INVISIBLE);
        txtxTimer.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        btnStart.setText("Skip");
        layoutGetready.setVisibility(View.VISIBLE);

        restCountDown.start();

        txtGetReady.setText("REST TIME");

    }

    private void showGetReady() {

        ex_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtxTimer.setVisibility(View.VISIBLE);

        layoutGetready.setVisibility(View.VISIBLE);

        txtGetReady.setText("GET READY");
        new CountDownTimer(6000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtCountdown.setText("" + (1-1000)/1000);
            }

            @Override
            public void onFinish() {
                showExercises();
            }
        }.start();
    }

    private void showExercises() {
        if (ex_id < list.size())
        {
            ex_image.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.VISIBLE);
            layoutGetready.setVisibility(View.INVISIBLE);

            if (warmUpDB.getSettingMode() == 0)
                exercisesEasyCountDown.start();
            else if (warmUpDB.getSettingMode() == 1)
                exercisesMediumCountDown.start();
            else if (warmUpDB.getSettingMode() == 2)
                exercisesHardCountDown.start();

            //Set data
            ex_image.setImageResource(list.get(ex_id).getImage_id());
            ex_name.setText(list.get(ex_id).getName());
        }
        else
        {
            showFinished();
        }
    }

    private void showFinished() {
        ex_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtxTimer.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        layoutGetready.setVisibility(View.VISIBLE);

        txtGetReady.setText("FINISHED !!!");
        txtCountdown.setText("Congratulation ! \nYou're done with today exercises");
        txtCountdown.setTextSize(20);

        warmUpDB.saveDay("" + Calendar.getInstance().getTimeInMillis());


    }

    //Countdown
    CountDownTimer exercisesEasyCountDown = new CountDownTimer(Common.TIME_LIMIT_EASY, 1000) {
        @Override
        public void onTick(long l) {
                  txtxTimer.setText("" + (l/1000));
        }

        @Override
        public void onFinish() {
            if (ex_id < list.size() - 1)
            {
                ex_id++;
                progressBar.setProgress(ex_id);
                txtxTimer.setText("");

                setWarmUpInformation(ex_id);
                btnStart.setText("Start");
            }
            else
            {
                showFinished();
            }
        }
    };

    CountDownTimer exercisesMediumCountDown = new CountDownTimer(Common.TIME_LIMIT_EASY, 1000) {
        @Override
        public void onTick(long l) {
            txtxTimer.setText("" + (l/1000));
        }

        @Override
        public void onFinish() {
            if (ex_id < list.size() - 1)
            {
                ex_id++;
                progressBar.setProgress(ex_id);
                txtxTimer.setText("");

                setWarmUpInformation(ex_id);
                btnStart.setText("Start");
            }
            else
            {
                showFinished();
            }
        }
    };

    CountDownTimer exercisesHardCountDown = new CountDownTimer(Common.TIME_LIMIT_EASY, 1000) {
        @Override
        public void onTick(long l) {
            txtxTimer.setText("" + (l/1000));
        }

        @Override
        public void onFinish() {
            if (ex_id < list.size() - 1)
            {
                ex_id++;
                progressBar.setProgress(ex_id);
                txtxTimer.setText("");

                setWarmUpInformation(ex_id);
                btnStart.setText("Start");
            }
            else
            {
                showFinished();
            }
        }
    };

    CountDownTimer restCountDown = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long l) {
            txtCountdown.setText("" + (l/1000));
        }

        @Override
        public void onFinish() {
           setWarmUpInformation(ex_id);
           showExercises();
        }
    };

    private void setWarmUpInformation(int id)
    {
        ex_image.setImageResource(list.get(id).getImage_id());
        ex_name.setText(list.get(id).getName());
        btnStart.setText("Start");

        ex_image.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        txtxTimer.setVisibility(View.VISIBLE);

        layoutGetready.setVisibility(View.INVISIBLE);


    }


    private void initData() {

        list.add(new Exercise(R.drawable.easy_pose, "Easy Pose"));
        list.add(new Exercise(R.drawable.cobra_pose, "Cobra Pose"));
        list.add(new Exercise(R.drawable.downward_facing_dog, "Downward_facing_dog Pose"));
        list.add(new Exercise(R.drawable.boat_pose, "Boat Pose"));
        list.add(new Exercise(R.drawable.half_pigeon, "Half_pigeon Pose"));
        list.add(new Exercise(R.drawable.low_lunge, "Low Lunge Pose"));
        list.add(new Exercise(R.drawable.upward_bow, "Upward Bow Pose"));
        list.add(new Exercise(R.drawable.crescent_lunge, "Crescent_lunge Pose"));
        list.add(new Exercise(R.drawable.warrior_pose, "Warrior Pose"));
        list.add(new Exercise(R.drawable.bow_pose, "Bow Pose"));
        list.add(new Exercise(R.drawable.warrior_pose_2, "Warrior Pose No.2"));
    }
}
