package com.example.equal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailArticleActivity extends AppCompatActivity {
    ImageButton imgBackButton;
    ImageView imgDetail;
    TextView tvTitle, tvDesc;
    private String title, desc, photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_article);

        imgBackButton = findViewById(R.id.imgBackButton);
        imgDetail = findViewById(R.id.imgDetail);
        tvTitle = findViewById(R.id.tvTitleDetail);
        tvDesc = findViewById(R.id.tvDescDetail);

        imgBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        title = getIntent().getStringExtra("ARTICLE_DETAIL_TITLE");
        desc = getIntent().getStringExtra("ARTICLE_DETAIL_DESCRIB");
        photo = getIntent().getStringExtra("ARTICLE_DETAIL_PHOTO");

        tvTitle.setText(title);
        tvDesc.setText(desc);

        //link sementara
        String link = photo;
        link = "http://10.0.2.2:8000/images/articles/"+link;
        Log.d("link", link);

        Glide.with(getBaseContext())
                .load(link)
                .into(imgDetail);

    }
}
