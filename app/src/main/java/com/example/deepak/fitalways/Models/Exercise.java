package com.example.deepak.fitalways.Models;

public class Exercise {
    int id;
    String name;
    boolean intro,rep;
    int sets_total;
    int rep_total;
    int rep_count;
    int set_count;
    int total_time;
    int rep_time;
    String video;

    public int getTime_taken() {
        return time_taken;
    }

    public void setTime_taken(int time_taken) {
        this.time_taken = time_taken;
    }

    int time_taken;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIntro() {
        return intro;
    }

    public void setIntro(boolean intro) {
        this.intro = intro;
    }

    public boolean isRep() {
        return rep;
    }

    public void setRep(boolean rep) {
        this.rep = rep;
    }

    public int getSets_total() {
        return sets_total;
    }

    public void setSets_total(int sets_total) {
        this.sets_total = sets_total;
    }

    public int getRep_total() {
        return rep_total;
    }

    public void setRep_total(int rep_total) {
        this.rep_total = rep_total;
    }

    public int getRep_count() {
        return rep_count;
    }

    public void setRep_count(int rep_count) {
        this.rep_count = rep_count;
    }

    public int getSet_count() {
        return set_count;
    }

    public void setSet_count(int set_count) {
        this.set_count = set_count;
    }

    public int getTotal_time() {
        return total_time;
    }

    public void setTotal_time(int total_time) {
        this.total_time = total_time;
    }

    public int getRep_time() {
        return rep_time;
    }

    public void setRep_time(int rep_time) {
        this.rep_time = rep_time;
    }


    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Exercise(int id, String name, boolean intro, boolean rep, int sets_total, int rep_total, int rep_count, int set_count, int total_time, int rep_time, int time_taken, String video) {
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.rep = rep;
        this.sets_total = sets_total;
        this.rep_total = rep_total;
        this.rep_count = rep_count;

        this.set_count = set_count;
        this.total_time = total_time;
        this.rep_time = rep_time;
        this.time_taken = time_taken;
        this.video = video;
    }
}
