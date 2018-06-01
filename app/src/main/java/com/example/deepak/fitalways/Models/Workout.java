package com.example.deepak.fitalways.Models;


public class Workout {
    int id;
    String name_workout;
    String date;
    int time,calories;

    public Workout(int id, String name_workout, String date, int time, int calories, String exercise) {
        this.id = id;
        this.name_workout = name_workout;
        this.date = date;
        this.time = time;
        this.calories = calories;
        this.exercise = exercise;
    }

    String exercise;
}
