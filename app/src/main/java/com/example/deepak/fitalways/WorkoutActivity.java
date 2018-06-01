package com.example.deepak.fitalways;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class WorkoutActivity extends AppCompatActivity {

    private boolean timerRunning = false;
    private CountDownTimer countDownTimer;
    private long start_time,time_left,rep_count;
    private TextView skipIntro,workoutName,startTimer,repInfo,setRepInfo,Timer;
    private ImageView playButton,stopButton,nextButton,muteButton;
    private VideoView videoView;
    public int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.video_player);
        videoView = findViewById(R.id.workout_video);

         playButton =  findViewById(R.id.playbutton);
         stopButton =  findViewById(R.id.stopbutton);
         nextButton =  findViewById(R.id.nextbutton);
         muteButton =  findViewById(R.id.button_mute);
        final boolean[] isMute = {false};

         skipIntro =  findViewById(R.id.skip_intro);
         workoutName =  findViewById(R.id.workout_name);
         startTimer =  findViewById(R.id.start_timer);
         repInfo =  findViewById(R.id.rep_info);
         setRepInfo =  findViewById(R.id.set_rep_info);
         Timer =  findViewById(R.id.timer_reps);

        index = getIntent().getExtras().getInt("Index");
        Log.d("workout","Value of ind"+String.valueOf(index));
        workoutName.setText(StartWorkoutActivity.exerciseList.get(index).getName());
        int setcount = StartWorkoutActivity.exerciseList.get(index).getSet_count();
        int repcount = StartWorkoutActivity.exerciseList.get(index).getRep_total();
        setRepInfo.setText("Set "+String.valueOf(setcount)+"/ Rep "+String.valueOf(repcount));

        //Mute button functionality
        muteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        if (!isMute[0]) {
                            mp.setVolume(0f, 0f);
                        } else {
                            mp.setVolume(100f, 100f);
                        }
                    }
                });
                if (!isMute[0]){
                    muteButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_volume_off_black_24dp));
                    isMute[0] = true;
                }
                else{
                    muteButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_volume_up_black_24dp));
                    isMute[0] = false;}
            }
        });

        playButton.setVisibility(View.GONE);
        stopButton.setVisibility(View.GONE);
        nextButton.setVisibility(View.GONE);

        final boolean intro_status = StartWorkoutActivity.exerciseList.get(index).isIntro();

//        String path = "video" + StartWorkoutActivity.exerciseList.get(index).getVideo();
        String video_path = "android.resource://" + getPackageName() + "/" + R.raw.video;
        videoView.setVideoPath(video_path);
        videoView.start();

        if (!intro_status) {
            rep_count = 0;

            //This will show the countdown for reps and timer based exercises
            if (!StartWorkoutActivity.exerciseList.get(index).isRep()){
                setRepInfo.setVisibility(View.INVISIBLE);
                repInfo.setVisibility(View.INVISIBLE);
                start_time = StartWorkoutActivity.exerciseList.get(index).getTotal_time()*1000+1000;
                time_left = start_time;
                start_timer(0);
            }
            else{

                repInfo.setText("No reps");
                start_time = StartWorkoutActivity.exerciseList.get(index).getRep_time()*1000+1000;
                time_left = start_time;
                start_timer(1);
            }

            videoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    videoView.pause();
                    pause_timer();
                    playButton.setVisibility(View.VISIBLE);
                    stopButton.setVisibility(View.VISIBLE);
                    nextButton.setVisibility(View.VISIBLE);

                }
            });

            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    videoView.start();
                    if (!StartWorkoutActivity.exerciseList.get(index).isRep())
                        start_timer(0);
                    else
                        start_timer(1);
                    playButton.setVisibility(View.GONE);
                    stopButton.setVisibility(View.GONE);
                    nextButton.setVisibility(View.GONE);
                }
            });

            stopButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pause_timer();
                    AlertDialog.Builder builder = new AlertDialog.Builder(WorkoutActivity.this);
                    builder.setTitle("Please confirm");
                    builder.setMessage("Do you really want to skip to the end of workout");

                    builder.setNeutralButton("No",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                        }
                    });
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            StartWorkoutActivity.exerciseList.get(index).setRep_count((int)(rep_count));
                            StartWorkoutActivity.exerciseList.get(index).setTime_taken((int)(start_time-time_left)/1000);
                            Intent intent = new Intent(WorkoutActivity.this,CompletedWorkout.class);
                            intent.putExtra("Index",index+1);
                            startActivity(intent);
                        }
                    });
                    builder.show();

                }
            });

            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pause_timer();
                    AlertDialog.Builder builder = new AlertDialog.Builder(WorkoutActivity.this);
                    builder.setTitle("Please confirm");

                    builder.setMessage("Skip to next Exercise ?");
                    if (index+1 == StartWorkoutActivity.exerciseList.size()){
                        builder.setMessage("Do you really want to skip to the end of workout");
                    }
                    builder.setNeutralButton("No",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            StartWorkoutActivity.exerciseList.get(index).setRep_count((int)(rep_count));
                            StartWorkoutActivity.exerciseList.get(index).setTime_taken((int)(start_time-time_left)/1000);
                            go_to_next_state(index);
                        }
                    });
                    builder.show();

                }
            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    pause_timer();
                    StartWorkoutActivity.exerciseList.get(index).setRep_count((int)(rep_count));
                    StartWorkoutActivity.exerciseList.get(index).setTime_taken((int)(start_time-time_left)/1000);
                    go_to_next_state(index);

                }
            });
        }

        else{

            skipIntro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    go_to_next_state(index);
                    finish();
                }
            });
            repInfo.setVisibility(View.INVISIBLE);
            setRepInfo.setVisibility(View.INVISIBLE);
            Timer.setVisibility(View.INVISIBLE);
            skipIntro.setVisibility(View.VISIBLE);
            workoutName.setVisibility(View.VISIBLE);
            startTimer.setVisibility(View.VISIBLE);
            time_left = StartWorkoutActivity.exerciseList.get(index).getTotal_time()*1000+1000;
            start_timer(2);
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    go_to_next_state(index);
                    finish();
                }
            });
        }

    }

    private void start_timer(final int i) {
        Log.e("time",String.valueOf(time_left));
        countDownTimer = new CountDownTimer(time_left,1000) {
            @Override
            public void onTick(long time_until_finished) {
                time_left = time_until_finished;
                switch (i) {
                    case 0: updateCountdownText(); break;
                    case 1: updateCountdownTextRepAndSet(); break;
                    case 2: updateSkipCounter(); break;
                }
            }

            @Override
            public void onFinish() {
                timerRunning = false;
            }
        }.start();
        timerRunning = true;
    }

    private void updateCountdownText() {
        int sec = (int)(time_left/1000);
        Timer.setText(Integer.toString(sec));
    }

    private void updateCountdownTextRepAndSet(){
        int sec = (int) (time_left/1000);
        Timer.setText(String.valueOf(sec));
        if (sec == 0){
        rep_count+=1;
        repInfo.startAnimation(AnimationUtils.loadAnimation(WorkoutActivity.this,android.R.anim.slide_in_left));
        repInfo.setText(String.valueOf(rep_count)+" reps");
        start_time = StartWorkoutActivity.exerciseList.get(index).getRep_time()*1000+1000;
        time_left = start_time;
        start_timer(1);}

    }

    private void updateSkipCounter(){
        int sec = (int)(time_left/1000);
        Log.d("String",""+String.valueOf(sec));
        startTimer.setText("Next Exercise Start in "+Integer.toString(sec));
    }

    private void pause_timer(){
        countDownTimer.cancel();
        timerRunning = false;

    }

    private void go_to_next_state(int index) {
        if (index+1<StartWorkoutActivity.exerciseList.size()){
            Intent intent = new Intent(WorkoutActivity.this,WorkoutActivity.class);
            intent.putExtra("Index",index+1);
            startActivity(intent);}
        else{
            Intent intent = new Intent(WorkoutActivity.this,CompletedWorkout.class);
            startActivity(intent);
        }
    }

}
