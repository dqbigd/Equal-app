package com.example.equal.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.equal.ArticleAdapter;
import com.example.equal.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView rvArticle;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        //recyclerview
//        rvArticle = view.findViewById(R.id.rvArticle);
//        rvArticle.setHasFixedSize(true);
//        list.addAll(PromoData.getListData());
//        snapRecyclerView();
//        recyclerProperties();

        return view;
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
