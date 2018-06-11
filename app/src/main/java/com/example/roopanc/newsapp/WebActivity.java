package com.example.roopanc.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import static com.example.roopanc.newsapp.HomeActivity.NEWS_LINK;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        String url = getIntent().getStringExtra(NEWS_LINK);

        WebView webView = findViewById(R.id.webview);
        webView.loadUrl(url);
    }
}
