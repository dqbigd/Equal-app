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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.equal.Preferences;
import com.example.equal.R;
import com.example.equal.Savejob.ResultSaveJob;

import java.util.ArrayList;

public class SaveJobAdapter extends RecyclerView.Adapter<SaveJobAdapter.SaveJobHolder> {
    private ArrayList<ResultSaveJob> listSaveJob;
    private Context context;

    public SaveJobAdapter(ArrayList<ResultSaveJob> list){
        this.listSaveJob = list;
    }

    @NonNull
    @Override
    public SaveJobHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_savejob, parent, false);
        context = view.getContext();
        return new SaveJobAdapter.SaveJobHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaveJobHolder holder, int position) {
        final ResultSaveJob saveJob = listSaveJob.get(position);
        final Preferences preferences = new Preferences(context);

        //link sementara
        String link = saveJob.getPhotoLogo();
        link = "http://10.0.2.2:8000/images/jobs/"+link;
        Log.d("link", link);

        Glide.with(holder.itemView.getContext())
                .load(link)
                .into(holder.imgLogo);
        holder.tvTitle.setText(saveJob.getTitle());
        holder.tvCompany.setText(saveJob.getCompany());

        holder.btnLamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recipients = saveJob.getEmail();
                String subject = "Lamaran Kerja pada perusahaan "+saveJob.getCompany();
                String message = "Perkenalkan nama saya "+preferences.getName()+", ingin melakukan lamaran kerja pada perusahaan "+saveJob.getCompany()+" dibidang "+saveJob.getFieldOfWork();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);

                intent.setType("message/rfc822");
                context.startActivity(Intent.createChooser(intent, "Choose an email client"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSaveJob.size();
    }

    public class SaveJobHolder extends RecyclerView.ViewHolder {
        ImageView imgLogo;
        TextView tvTitle, tvCompany;
        Button btnLamar, btnHapus;
        public SaveJobHolder(@NonNull View itemView) {
            super(itemView);
            imgLogo = itemView.findViewById(R.id.imgCompanyLogo);
            tvTitle = itemView.findViewById(R.id.tvTitleJob);
            tvCompany = itemView.findViewById(R.id.tvCompany);
            btnLamar = itemView.findViewById(R.id.btnLamar);
            btnHapus = itemView.findViewById(R.id.btnHapus);
        }
    }
}
