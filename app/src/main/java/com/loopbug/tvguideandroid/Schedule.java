package com.loopbug.tvguideandroid;

/**
 * Created by joaoluis on 21/07/15.
 */
public class Schedule {

    private int id;
    private String name;
    private int duration;
    private long startHour;
    private long endHour;
    private boolean isDummy;

    public Schedule(int id, String name, int duration, long startHour, long endHour, boolean isDummy) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.startHour = startHour;
        this.endHour = endHour;
        this.isDummy = isDummy;
    }

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

    public long getStartHour() {
        return startHour;
    }

    public void setStartHour(long startHour) {
        this.startHour = startHour;
    }

    public long getEndHour() {
        return endHour;
    }

    public void setEndHour(long endHour) {
        this.endHour = endHour;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isDummy() {
        return isDummy;
    }

    public void setIsDummy(boolean isDummy) {
        this.isDummy = isDummy;
    }
}
