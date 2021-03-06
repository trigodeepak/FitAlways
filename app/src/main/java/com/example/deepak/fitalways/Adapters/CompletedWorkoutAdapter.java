package com.example.deepak.fitalways.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deepak.fitalways.Models.Exercise;
import com.example.deepak.fitalways.R;

import java.util.ArrayList;

public class CompletedWorkoutAdapter extends RecyclerView.Adapter<CompletedWorkoutAdapter.MyViewHolder> {

        ArrayList<Exercise> exerciseList;
public CompletedWorkoutAdapter(ArrayList<Exercise> exerciseList){
        this.exerciseList = exerciseList;
        }

public class MyViewHolder extends RecyclerView.ViewHolder{
    TextView exerciseName,exerciseInfo;
    public MyViewHolder(View itemView) {
        super(itemView);
        exerciseName = itemView.findViewById(R.id.list_exercise_name);
        exerciseInfo = itemView.findViewById(R.id.list_exercise_info);
    }
}
    @Override
    public CompletedWorkoutAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_start_workout,parent,false);
        return new CompletedWorkoutAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CompletedWorkoutAdapter.MyViewHolder holder, int position) {

            holder.exerciseName.setText(exerciseList.get(position).getName());
            if (exerciseList.get(position).isRep()){
                holder.exerciseInfo.setText(String.valueOf(exerciseList.get(position).getRep_count())+" reps");
            }
            else{
                holder.exerciseInfo.setText(String.valueOf(exerciseList.get(position).getTime_taken())+" s");
            }
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
