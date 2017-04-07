
package com.xpectra.tvmaze.schemas;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Schedule {

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("days")
    @Expose
    private List<String> days = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Schedule() {
    }

    /**
     * 
     * @param time
     * @param days
     */
    public Schedule(String time, List<String> days) {
        super();
        this.time = time;
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
