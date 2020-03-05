package com.example.equal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.equal.Api.ApiClient;
import com.example.equal.Api.ApiInterface;
import com.example.equal.DetailJobActivity;
import com.example.equal.Model.Job;
import com.example.equal.Preferences;
import com.example.equal.R;
import com.example.equal.Savejob.ResultSaveJob;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobHolder> {
    private ArrayList<Job> listJob;
//    private ArrayList<ResultSaveJob> listSaveJob;
    private Context context;
    private String title, company, photo_logo,city, field, salary, desc, requirements, qualification;
    private Integer job_id, user_id;

    final ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

    public JobAdapter(ArrayList<Job> list){
        this.listJob = list;
    }

    @NonNull
    @Override
    public JobHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job, parent, false);
        context = view.getContext();
        return new JobAdapter.JobHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final JobHolder holder, int position) {
        final Job job= listJob.get(position);
        final Preferences preferences = new Preferences(context);

        //link sementara
        String link = job.getPhotoLogo();
        link = "http://10.0.2.2:8000/images/jobs/"+link;
        Log.d("link", link);

        Glide.with(holder.itemView.getContext())
                .load(link)
                .into(holder.imgLogo);
        holder.tvTitle.setText(job.getTitle());
        holder.tvCompany.setText(job.getCompany());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDetail = new Intent(context, DetailJobActivity.class);
                title = listJob.get(holder.getAdapterPosition()).getTitle();
                company =listJob.get(holder.getAdapterPosition()).getCompany();
                photo_logo = listJob.get(holder.getAdapterPosition()).getPhotoLogo();
                city = listJob.get(holder.getAdapterPosition()).getCity();
                field = listJob.get(holder.getAdapterPosition()).getFieldOfWork();
                salary = listJob.get(holder.getAdapterPosition()).getSalary();
                desc = listJob.get(holder.getAdapterPosition()).getDesc();
                requirements = listJob.get(holder.getAdapterPosition()).getRequirements();
                qualification = listJob.get(holder.getAdapterPosition()).getQualification();

                intentDetail.putExtra("JOB_DETAIL_TITLE", title);
                intentDetail.putExtra("JOB_DETAIL_COMPANY", company);
                intentDetail.putExtra("JOB_DETAIL_PHOTO", photo_logo);
                intentDetail.putExtra("JOB_DETAIL_CITY", city);
                intentDetail.putExtra("JOB_DETAIL_FIELD", field);
                intentDetail.putExtra("JOB_DETAIL_SALARY", salary);
                intentDetail.putExtra("JOB_DETAIL_DESC", desc);
                intentDetail.putExtra("JOB_DETAIL_REQUIREMENTS", requirements);
                intentDetail.putExtra("JOB_DETAIL_QUALIFICATION", qualification);

                context.startActivity(intentDetail);
            }
        });

        holder.btnLamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recipients = job.getEmail();
                String subject = "Lamaran Kerja pada perusahaan "+job.getCompany();
                String message = "Perkenalkan nama saya "+preferences.getName()+", ingin melakukan lamaran kerja pada perusahaan "+job.getCompany()+" dibidang "+job.getFieldOfWork();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);

                intent.setType("message/rfc822");
                context.startActivity(Intent.createChooser(intent, "Choose an email client"));
            }
        });

        holder.btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean notyet = true;

                user_id = preferences.getUserId();
                job_id = listJob.get(holder.getAdapterPosition()).getId();

//                for (int i = 0;i>listJob.size();i++){
//                    Log.d("sebelum add", listSaveJob.get(i).getId().toString());
//                }

//                Toast.makeText(holder.itemView.getContext(), String.valueOf(job_id)+user_id, Toast.LENGTH_SHORT).show();

//                if (notyet){
//
//                }
                Call<ResultSaveJob> call = apiInterface.setSaveJob(user_id, job_id);
                call.enqueue(new Callback<ResultSaveJob>() {
                    @Override
                    public void onResponse(Call<ResultSaveJob> call, Response<ResultSaveJob> response) {
                        if (response.isSuccessful()){
//                            response.body();
                            Toast.makeText(holder.itemView.getContext(), "Lowongan kerja telah disimpan!", Toast.LENGTH_SHORT).show();
                            Log.d("response", String.valueOf(response.code())+" "+response.message());
                        }else{
                            Toast.makeText(holder.itemView.getContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                            Log.d("simploker no response", String.valueOf(Integer.valueOf(response.code())));
                            Log.d("simploker no response", response.message().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultSaveJob> call, Throwable t) {
                        Toast.makeText(holder.itemView.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("simpan Loker Failure", t.getMessage());
                    }
                });
            }
        });

    }



    @Override
    public int getItemCount() {
        return listJob.size();
    }

    public class JobHolder extends RecyclerView.ViewHolder {
        ImageView imgLogo;
        TextView tvTitle, tvCompany;
        Button btnLamar, btnSimpan;

        public JobHolder(@NonNull View itemView) {
            super(itemView);
            imgLogo = itemView.findViewById(R.id.imgCompanyLogo);
            tvTitle = itemView.findViewById(R.id.tvTitleJob);
            tvCompany = itemView.findViewById(R.id.tvCompany);
            btnLamar = itemView.findViewById(R.id.btnLamar);
            btnSimpan = itemView.findViewById(R.id.btnSimpan);
        }
    }
}
