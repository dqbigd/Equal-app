package com.example.equal;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder> {
    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ArticleHolder extends RecyclerView.ViewHolder {
        public ArticleHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
