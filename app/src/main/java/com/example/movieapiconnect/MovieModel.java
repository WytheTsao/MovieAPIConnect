package com.example.movieapiconnect;


import java.util.List;

public class MovieModel {

    private String about;
    private String name;
    private String engName;
    private String runTime;
    private String type;
    private String comeOutDate;
    private String poster;
    private List<String> videos;

    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public String getEngName() {
        return engName;
    }

    public String getRunTime() {
        return runTime;
    }

    public String getType() {
        return type;
    }

    public String getComeOutDate() {
        return comeOutDate;
    }

    public String getPoster() {
        return poster;
    }

    public List<String> getVideos() {
        return videos;
    }
}
