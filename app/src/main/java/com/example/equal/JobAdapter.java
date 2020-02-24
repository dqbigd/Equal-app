package com.example.equal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.equal.Model.Job;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobHolder> {
    private ArrayList<Job> listJob;
    private Context context;

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
    public void onBindViewHolder(@NonNull JobHolder holder, int position) {
        Job job= listJob.get(position);

        //link sementara
        String link = job.getPhotoLogo();
        link = "http://10.0.2.2:8000/images/articles/"+link;
        Log.d("link", link);

        Glide.with(holder.itemView.getContext())
                .load(link)
                .into(holder.imgLogo);
        holder.tvTitle.setText(job.getTitle());



    }

    @Override
    public int getItemCount() {
        return listJob.size();
    }

    public class JobHolder extends RecyclerView.ViewHolder {
        CircleImageView imgLogo;
        TextView tvTitle;

        public JobHolder(@NonNull View itemView) {
            super(itemView);
            itemView.findViewById(R.id.imgJob);
            itemView.findViewById(R.id.tvTitleJob);
        }
    }
}
