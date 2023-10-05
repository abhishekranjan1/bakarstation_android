package com.java.bakarstation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kwabenaberko.newsapilib.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>{


    List<Article> articleList;
    NewsRecyclerAdapter(List<Article> articleList){
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_row,parent,false);
       return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
    //Here is actual data coming from the api will bind with news_recycler_row

        Article article = articleList.get(position);
        Log.i("TITLE",article.getTitle());
        Log.i("SOURCE_NAME",article.getSource().getName());
        holder.titleTextView.setText(article.getTitle());
        holder.sourceTextView.setText(article.getSource().getName());
        Picasso.get().load(article.getUrlToImage())
                .error(R.drawable.no_image_icon)
                .placeholder(R.drawable.no_image_icon)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    //Whenever we get new data we need to update the recycler View and pull he latest data into the recyclerView
    void updateData(List<Article> data){
        articleList.clear();
        articleList.addAll(data);
    }

    //Next class and methods are to initialize the itemView so Adapter could use them. Adapter will create the binding between actual content and RecyclerView
    class NewsViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView titleTextView, sourceTextView;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.article_title);
            sourceTextView = itemView.findViewById(R.id.article_source);
            imageView = itemView.findViewById(R.id.article_image_view);
        }
    }
}
