package com.example.roopanc.newsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

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

import static com.example.roopanc.newsapp.HomeActivity.NEWS_LINK;

public class NewsActivity extends AppCompatActivity {

    private NewsAdapter newsAdapter;
    private List<News> newsList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        progressBar = findViewById(R.id.progressbar);

        RecyclerView recyclerView = findViewById(R.id.newsrcview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsList = new ArrayList<>();
        newsAdapter = new NewsAdapter(this, newsList);

        recyclerView.setAdapter(newsAdapter);

        String newsLink = getIntent().getStringExtra(NEWS_LINK);
        Log.d("newsLink", " "+ newsLink);

        new JsonTask().execute(newsLink);

    }


    private void getDatafromJSON(String jsonStr) {

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
    }

    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);

                }

                return buffer.toString();


            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);
            Log.d("JSON", result + " ");
            getDatafromJSON(result);
        }
    }
}
