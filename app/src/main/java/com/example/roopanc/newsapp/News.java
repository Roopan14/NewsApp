package com.example.roopanc.newsapp;

/**
 * Created by Roopan C on 6/1/2018.
 */

public class News {

    private String title;
    private String imageurl;
    private String description;
    private String newslink;

    public News()
    {

    }

    public News(String title, String description, String imageurl, String newslink)
    {
        this.title = title;
        this.description = description;
        this.imageurl = imageurl;
        this.newslink = newslink;
    }

    public String getTitle() {
        return title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getDescription() {
        return description;
    }

    public String getNewslink() {
        return newslink;
    }


}
