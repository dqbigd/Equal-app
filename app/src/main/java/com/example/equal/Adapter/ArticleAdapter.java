package com.example.equal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.equal.DetailArticleActivity;
import com.example.equal.Model.Article;
import com.example.equal.R;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder> {
    private ArrayList<Article> listArticle ;
    private Context context;
    private String getTitle, getDescrib, getPhoto;

    public ArticleAdapter(ArrayList<Article> list){
        this.listArticle = list;
    }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        context = view.getContext();
        return new ArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArticleHolder holder, int position) {
        Article article = listArticle.get(position);

        //link sementara
        String link = article.getPhoto();
        link = "https://equal.lug-surabaya.com/images/articles/"+link;
        Log.d("link", link);

        Glide.with(holder.itemView.getContext())
                .load(link)
                .into(holder.imgPhoto);
        holder.tvTitle.setText(article.getTitle());
        holder.tvDesc.setText(article.getDesc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDetail = new Intent(context, DetailArticleActivity.class);
                getTitle = listArticle.get(holder.getAdapterPosition()).getTitle();
                getDescrib = listArticle.get(holder.getAdapterPosition()).getDesc();
                getPhoto = listArticle.get(holder.getAdapterPosition()).getPhoto();

                intentDetail.putExtra("ARTICLE_DETAIL_TITLE", getTitle);
                intentDetail.putExtra("ARTICLE_DETAIL_DESCRIB", getDescrib);
                intentDetail.putExtra("ARTICLE_DETAIL_PHOTO", getPhoto);

                context.startActivity(intentDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listArticle.size();
    }

    public class ArticleHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvTitle, tvDesc;

        public ArticleHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.imgArticle);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }
}
