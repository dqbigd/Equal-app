package com.example.equal.Fragment;


import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.example.equal.Api.ApiClient;
import com.example.equal.Api.ApiInterface;
import com.example.equal.JobAdapter;
import com.example.equal.Model.Article;
import com.example.equal.Model.Job;
import com.example.equal.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class JobFragment extends Fragment {
    private RecyclerView rvJob;

    private ArrayList<Job> jobList = new ArrayList<>();
    private JobAdapter jobAdapter;
    final ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);


    public JobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job, container, false);

        rvJob = view.findViewById(R.id.rvJob);

        //recyclerview
        snapRecyclerView();
        rvJob.setLayoutManager(new LinearLayoutManager(getContext()));
        rvJob.setAdapter(jobAdapter);
        loadJob();

        return view;
    }

    private void loadJob() {
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setTitle("Memuat Lowongan Kerja");
        dialog.setMessage("Loading ...");
        dialog.setCancelable(true);
        dialog.show();

        Call<List<Job>> call = apiInterface.getJob();
        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful()){
                    dialog.hide();
                    jobList = new ArrayList<>(response.body());
                    jobAdapter = new JobAdapter(jobList);
                    rvJob.setAdapter(jobAdapter);
                    Log.d("Success ", response.body().toString());
                }else {
                    Toast.makeText(getActivity(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                    Log.d("Response Error", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.d("Failure Load", t.getMessage());
            }
        });
    }

    private void snapRecyclerView() {
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(rvJob);
    }

}
