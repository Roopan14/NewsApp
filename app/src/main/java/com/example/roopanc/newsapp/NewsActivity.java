package com.example.roopanc.newsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.roopanc.newsapp.HomeActivity.NEWS_LINK;

public class NewsActivity extends AppCompatActivity {

    public NewsAdapter newsAdapter;
    public List<News> newsList;
    public ProgressBar progressBar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        progressBar = findViewById(R.id.progressbar);

        recyclerView = findViewById(R.id.newsrcview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        String newsLink = getIntent().getStringExtra(NEWS_LINK);
        Log.d("newsLink", " "+ newsLink);

        if (Connectivity.getInstance(getApplicationContext()).isOnline())
        {
            //new JsonTask().execute(newsLink);
            getNews(newsLink);
        }
        else {
            Toast.makeText(this, "Check your Internet Connectivity", Toast.LENGTH_SHORT).show();
        }

    }

    private void getNews(final String newsLink) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<NewsJson> call = null;

        if (newsLink.contains("apple"))
        {
            call = retrofitAPI.getBNews();
        }
        else if (newsLink.contains("bitcoin"))
        {
            call = retrofitAPI.getBtcNews();
        }
        else if (newsLink.contains("tech"))
        {
            call = retrofitAPI.getTechNews();
        }
        else {
            call = retrofitAPI.getBNews();
        }


        call.enqueue(new Callback<NewsJson>() {
            @Override
            public void onResponse(Call<NewsJson> call, Response<NewsJson> response) {

                NewsJson newsJson = response.body();
                if (newsJson != null) {
                    newsList = new ArrayList<>(newsJson.getArticles());
                    newsAdapter = new NewsAdapter(NewsActivity.this, newsList);
                    recyclerView.setAdapter(newsAdapter);
                }
                else {
                    Toast.makeText(NewsActivity.this, "No data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsJson> call, Throwable t) {
                Toast.makeText(NewsActivity.this, "failure", Toast.LENGTH_SHORT).show();
                Log.d("failure_r", "onFailure: "+t.toString());
            }
        });

    }


    /*public static void getDatafromJSON(String jsonStr) {

        try {
            JSONObject objectRoot = new JSONObject(jsonStr);
            JSONArray jsonArticleArray = objectRoot.getJSONArray("articles");
            for (int i = 0; i<jsonArticleArray.length(); i++){
                JSONObject object = jsonArticleArray.getJSONObject(i);
                String title = object.getString("title");
                String description = object.getString("description");
                String url = object.getString("url");
                String imgurl = object.getString("urlToImage");

                Log.d("json title", "" + title);
                News news = new News(title, description, imgurl, url);
                newsList.add(news);
            }
            newsAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/


}
