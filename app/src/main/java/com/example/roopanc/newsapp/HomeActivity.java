package com.example.roopanc.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout bCardView, appleCard, bitcoinCard, tcCard;
    public static String NEWS_LINK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bCardView = findViewById(R.id.businesscard);
        appleCard = findViewById(R.id.applecard);
        bitcoinCard = findViewById(R.id.bitcoincard);
        tcCard = findViewById(R.id.techcard);

        bCardView.setOnClickListener(this);
        appleCard.setOnClickListener(this);
        bitcoinCard.setOnClickListener(this);
        tcCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent = new Intent(HomeActivity.this, NewsActivity.class);

        switch (id)
        {
            case R.id.businesscard:
                //intent.putExtra(NEWS_LINK, getString(R.string.business_link));
                intent.putExtra(NEWS_LINK, getString(R.string.business_rlink));
                startActivity(intent);
                break;

            case R.id.applecard:
                //intent.putExtra(NEWS_LINK, getString(R.string.apple_link));
                intent.putExtra(NEWS_LINK, getString(R.string.apple_rlink));
                startActivity(intent);
                break;

            case R.id.bitcoincard:
                //intent.putExtra(NEWS_LINK, getString(R.string.bitcoin_link));
                intent.putExtra(NEWS_LINK, getString(R.string.bitcoin_rlink));
                startActivity(intent);
                break;

            case R.id.techcard:
                //intent.putExtra(NEWS_LINK, getString(R.string.tech_link));
                intent.putExtra(NEWS_LINK, getString(R.string.tech_rlink));
                startActivity(intent);
                break;
        }

    }
}
