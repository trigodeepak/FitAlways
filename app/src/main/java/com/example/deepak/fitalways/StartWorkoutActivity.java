package com.example.deepak.fitalways;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.deepak.fitalways.Adapters.StartWorkoutAdapter;
import com.example.deepak.fitalways.Models.Exercise;

import java.util.ArrayList;

public class StartWorkoutActivity extends AppCompatActivity {
    public static final ArrayList<Exercise> exerciseList = populateData();
    private RecyclerView recyclerView;
    private StartWorkoutAdapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_workout);
        Button GoButton = findViewById(R.id.go_to_workout);
        GoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartWorkoutActivity.this,WorkoutActivity.class);
                intent.putExtra("Index",0);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.list_start_workout);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        ArrayList<Exercise> exercise = new ArrayList<Exercise>();
        for(int i = 0;i<exerciseList.size();i++){
            if (!exerciseList.get(i).isIntro()){
                exercise.add(exerciseList.get(i));
            }
        }
        mAdapter = new StartWorkoutAdapter(exercise);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private static ArrayList<Exercise> populateData() {
        ArrayList<Exercise> exerciseList = new ArrayList<Exercise>();
        exerciseList.add(new Exercise(0,"Barbell Curl",true,true,3,4,0,1,20,5,0,""));
        exerciseList.add(new Exercise(1,"Barbell Curl",false,true,3,4,0,1,20,5,0,""));
        exerciseList.add(new Exercise(2,"Jumping Jacks",true,false,0,12,3,1,19,3,0,"2"));
        exerciseList.add(new Exercise(3,"Jumping Jacks",false,false,0,12,3,1,19,3,0,"2"));
        return exerciseList;
    }
}
