package com.loopbug.tvguideandroid;

import java.util.ArrayList;

/**
 * Created by joaoluis on 21/07/15.
 */
public class Channel {

    private int id;
    private String name;
    private ArrayList<Schedule> mSchedule;

    public Channel(int id, String name, ArrayList<Schedule> mSchedule) {
        this.id = id;
        this.name = name;
        this.mSchedule = mSchedule;
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

    public ArrayList<Schedule> getSchedule() {
        return mSchedule;
    }

    public void setSchedule(ArrayList<Schedule> mSchedule) {
        this.mSchedule = mSchedule;
    }
}
