package com.example.roopanc.newsapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Roopan C on 6/26/2018.
 */

public interface RetrofitAPI {

    String BASE_URL = "https://newsapi.org/v2/";

    @GET("everything?q=apple&from=2018-06-25&to=2018-06-25&sortBy=popularity&apiKey=ede7912cd5154b478f22f80a8bb1a65a")
    Call<NewsJson> getAppleNews();

    @GET("top-headlines?sources=techcrunch&apiKey=ede7912cd5154b478f22f80a8bb1a65a")
    Call<NewsJson> getTechNews();

    @GET("everything?q=bitcoin&sortBy=publishedAt&apiKey=ede7912cd5154b478f22f80a8bb1a65a")
    Call<NewsJson> getBtcNews();

    @GET("top-headlines?country=us&category=business&apiKey=ede7912cd5154b478f22f80a8bb1a65a")
    Call<NewsJson> getBNews();

}
