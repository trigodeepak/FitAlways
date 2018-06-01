package com.example.deepak.fitalways;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.deepak.fitalways.Models.Exercise;

import java.util.ArrayList;

public class StartWorkoutActivity extends AppCompatActivity {
    public static final ArrayList<Exercise> exerciseList = populateData();
    private int i;

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
    }

    private static ArrayList<Exercise> populateData() {
        ArrayList<Exercise> exerciseList = new ArrayList<Exercise>();
        exerciseList.add(new Exercise(0,"Barbell Curl",true,true,3,4,0,1,20,5,0,""));
        exerciseList.add(new Exercise(1,"Barbell Curl",false,true,3,4,0,1,20,5,0,""));
        exerciseList.add(new Exercise(2,"Jumping Jacks",true,false,0,12,3,1,30,3,0,"2"));
        exerciseList.add(new Exercise(3,"Jumping Jacks",false,false,0,12,3,1,30,3,0,"2"));
        return exerciseList;
    }
}
