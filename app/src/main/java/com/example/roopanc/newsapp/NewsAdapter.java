package com.example.roopanc.newsapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import static com.example.roopanc.newsapp.HomeActivity.NEWS_LINK;

/**
 * Created by Roopan C on 6/1/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context context;
    private List<News> newsList;

    public NewsAdapter(Context context, List<News> newsList)
    {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newscard, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final News news = newsList.get(position);
        holder.titleTV.setText(news.getTitle());
        holder.descriptionTV.setText(news.getDescription());

        Glide.with(context).load(news.getImageurl()).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra(NEWS_LINK, news.getNewslink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView titleTV, descriptionTV;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.textHeading);
            descriptionTV = itemView.findViewById(R.id.textDesc);
            imageView = itemView.findViewById(R.id.cardimg);
            cardView = itemView.findViewById(R.id.newscardvw);
        }
    }
}
