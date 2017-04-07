
package com.xpectra.tvmaze.schemas;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Show implements Comparable<Show> {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("genres")
    @Expose
    private List<String> genres = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("runtime")
    @Expose
    private Integer runtime;
    @SerializedName("schedule")
    @Expose
    private Schedule schedule;
    @SerializedName("rating")
    @Expose
    private Rating rating;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("summary")
    @Expose
    private String summary;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Show() {
    }

    /**
     * 
     * @param summary
     * @param id
     * @param schedule
     * @param genres
     * @param status
     * @param name
     * @param image
     * @param runtime
     * @param rating
     * @param url
     */
    public Show(Integer id, String url, String name, List<String> genres, String status, Integer runtime, Schedule schedule, Rating rating, Image image, String summary) {
        super();
        this.id = id;
        this.url = url;
        this.name = name;
        this.genres = genres;
        this.status = status;
        this.runtime = runtime;
        this.schedule = schedule;
        this.rating = rating;
        this.image = image;
        this.summary = summary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


    @Override
    public int compareTo(Show show) {
        return this.name.compareTo(show.getName());
    }
}
