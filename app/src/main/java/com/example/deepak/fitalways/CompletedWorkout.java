package com.example.deepak.fitalways;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.deepak.fitalways.Adapters.CompletedWorkoutAdapter;
import com.example.deepak.fitalways.Models.Exercise;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CompletedWorkout extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CompletedWorkoutAdapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;
    private TextView workoutName;
    private Button FinishWorkout;
    private DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_workout);

        workoutName = findViewById(R.id.workout_name);
        workoutName.setText("Congrats you have completed the workout !");
        FinishWorkout = findViewById(R.id.finish_workout);

        FinishWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CompletedWorkout.this,MainActivity.class);
                startActivity(intent);
            }
        });

        String result = "";
        recyclerView = (RecyclerView) findViewById(R.id.list_start_workout);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        ArrayList<Exercise> exercise = new ArrayList<Exercise>();
        for(int i = 0;i<StartWorkoutActivity.exerciseList.size();i++){
            Exercise e = StartWorkoutActivity.exerciseList.get(i);
            if (!e.isIntro()){

                exercise.add(e);
                result = result+e.getName()+" ";
                if (e.isRep()){
                    result = result+e.getRep_count()+" reps ";
                }
                else
                    result = result+e.getTime_taken()+" s ";
            }
        }
        mAdapter = new CompletedWorkoutAdapter(exercise);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mydb = new DatabaseHelper(this);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

        mydb.insertData("Classic Workout",df.format(Calendar.getInstance().getTime()),80,45,result);
    }
}
