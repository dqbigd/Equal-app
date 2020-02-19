package com.example.equal.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.equal.Api.ApiClient;
import com.example.equal.Api.ApiInterface;
import com.example.equal.ArticleAdapter;
import com.example.equal.Model.Article;
import com.example.equal.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView rvArticle;
    
    final ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
    private String key_api = "PdSgVkYp3s6v9y$B";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        loadArticle();
        //recyclerview
        rvArticle = view.findViewById(R.id.rvArticle);
//        rvArticle.setHasFixedSize(true);
//        list.addAll(PromoData.getListData());
//        snapRecyclerView();
//        recyclerProperties();


        return view;
    }

    private void loadArticle() {
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setTitle("Memuat Artikel");
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();

        Call<Article> call = apiInterface.getArticle(key_api+"/article/");
        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                dialog.hide();
                articleList = new ArrayList<>();
                articleList = response.body().getId();
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {

            }
        });

    }

    private void snapRecyclerView() {
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(rvArticle);

    }

//    private void recyclerProperties() {
//        rvArticle.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//        ArticleAdapter articleAdapter= new ArticleAdapter(list);
//        rvArticle.setAdapter(promoAdapter);
//    }

}
