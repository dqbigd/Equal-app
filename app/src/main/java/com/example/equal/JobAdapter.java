package com.example.equal;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.equal.Model.Job;

import java.util.ArrayList;


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

        holder.btnLamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recipients = job.getEmail();
                String subject = "Lamaran Kerja pada perusahaan "+job.getCompany();
                String message = "Perkenalkan nama saya "+preferences.getName()+", ingin melakukan lamaran kerja pada perusahaan "+job.getCompany()+" dibidang"+job.getFieldOfWork();

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


            }
        });

    }


    @Override
    public int getItemCount() {
        return listJob.size();
    }

    public class JobHolder extends RecyclerView.ViewHolder {
        ImageView imgLogo;
        TextView tvTitle;
        Button btnLamar, btnSimpan;

        public JobHolder(@NonNull View itemView) {
            super(itemView);
            imgLogo = itemView.findViewById(R.id.imgJob);
            tvTitle = itemView.findViewById(R.id.tvTitleJob);
            btnLamar = itemView.findViewById(R.id.btnLamar);
            btnSimpan = itemView.findViewById(R.id.btnSimpan);
        }
    }
}
