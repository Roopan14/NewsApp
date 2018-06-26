package com.example.roopanc.newsapp;

import java.util.List;

/**
 * Created by Roopan C on 6/26/2018.
 */

public class NewsJson {

    private String status;
    private String totalResults;
    private List<News> articles;

    public NewsJson(String status, String totalResults, List<News> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public List<News> getArticles() {
        return articles;
    }
}
