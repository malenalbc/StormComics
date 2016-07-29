package com.malenalbc.superherotest.data.model;


import java.util.List;

public class Character extends Result{
    private String name;
    private String description;
    private List<Url> urls;
    private Image thumbnail;

    public String getName () {
        return name;
    }

    public String getDescription () {
        return description;
    }

    public List<Url> getUrls () {
        return urls;
    }

    public Image getThumbnail () {
        return thumbnail;
    }
}
