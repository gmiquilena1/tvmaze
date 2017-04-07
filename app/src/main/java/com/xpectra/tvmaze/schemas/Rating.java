
package com.xpectra.tvmaze.schemas;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("average")
    @Expose
    private Double average;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Rating() {
    }

    /**
     * 
     * @param average
     */
    public Rating(Double average) {
        super();
        this.average = average;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
