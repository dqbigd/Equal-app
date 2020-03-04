package com.example.equal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.equal.Adapter.SaveJobAdapter;
import com.example.equal.Api.ApiClient;
import com.example.equal.Api.ApiInterface;
import com.example.equal.Savejob.ResponseSaveJob;
import com.example.equal.Savejob.ResultSaveJob;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveJobActivity extends AppCompatActivity {
    ImageButton imgBackButton;
    private RecyclerView rvSaveJob;

    private ArrayList<ResultSaveJob> SaveJobList = new ArrayList<>();
    private SaveJobAdapter saveJobAdapter;
    final ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_job);

        rvSaveJob = findViewById(R.id.rvSaveJob);
        imgBackButton = findViewById(R.id.imgBackButton);

        //recyclerview
        snapRecyclerView();
        rvSaveJob.setLayoutManager(new LinearLayoutManager(SaveJobActivity.this));
        rvSaveJob.setAdapter(saveJobAdapter);
        loadSaveJob();

        imgBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadSaveJob() {
        Preferences preferences = new Preferences(SaveJobActivity.this);

        String key_api = "PdSgVkYp3s6v9y$B";
        final Integer user_id = preferences.getUserId();

        final ProgressDialog dialog = new ProgressDialog(SaveJobActivity.this);
        dialog.setTitle("Memuat Loker Tersimpan");
        dialog.setMessage("Loading ...");
        dialog.setCancelable(true);
        dialog.show();

        Call<ResponseSaveJob> call = apiInterface.getSaveJob(key_api+"/job_user/"+user_id);
        call.enqueue(new Callback<ResponseSaveJob>() {
            @Override
            public void onResponse(Call<ResponseSaveJob> call, Response<ResponseSaveJob> response) {
                if(response.isSuccessful()){
                    if(response.body().getId().equals(user_id)){
//                    response.body().getResultSaveJob();
                        dialog.hide();
                        SaveJobList = new ArrayList<>(response.body().getResultSaveJob());
                        saveJobAdapter = new SaveJobAdapter(SaveJobList);
                        rvSaveJob.setAdapter(saveJobAdapter);
                        Log.d("Success ", response.body().getResultSaveJob().toString());
                    }
                }else{
                    Toast.makeText(SaveJobActivity.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                    Log.d("Saveloker no resp", response.errorBody().toString());
                    Log.d("Saveloker no resp", String.valueOf(Integer.valueOf(response.code())));
                    Log.d("Saveloker no resp", response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseSaveJob> call, Throwable t) {
                Log.d("Failure Load", t.getMessage());
            }
        });
    }

    private void snapRecyclerView() {
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(rvSaveJob);
    }
}
