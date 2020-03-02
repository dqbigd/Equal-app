package com.example.equal.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.equal.Api.ApiClient;
import com.example.equal.Api.ApiInterface;
import com.example.equal.Adapter.ArticleAdapter;
import com.example.equal.LaporActivity;
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
    private ImageView imgLapor;

    private ArrayList<Article> articleList = new ArrayList<>();
    private ArticleAdapter articleAdapter;
    final ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imgLapor = view.findViewById(R.id.imgLapor);
        rvArticle = view.findViewById(R.id.rvArticle);

        //recyclerview
//        rvArticle.setHasFixedSize(true);
        snapRecyclerView();
        rvArticle.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        rvArticle.setAdapter(articleAdapter);
        loadArticle();

        imgLapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), LaporActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

    private void loadArticle() {
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setTitle("Memuat Artikel");
        dialog.setMessage("Loading ...");
        dialog.setCancelable(true);
        dialog.show();

        Call<List<Article>> call = apiInterface.getArticle();
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if (response.isSuccessful()){
                    dialog.hide();
                    articleList = new ArrayList<>(response.body());
                    articleAdapter = new ArticleAdapter(articleList);
                    rvArticle.setAdapter(articleAdapter);
                    Log.d("Success ", response.body().toString());
                }else {
                    Toast.makeText(getActivity(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                    Log.d("Response Error", response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Failure Load", t.getMessage());
            }
        });

    }

    private void snapRecyclerView() {
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(rvArticle);

    }


}
