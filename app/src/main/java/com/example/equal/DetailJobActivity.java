package com.example.equal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailJobActivity extends AppCompatActivity {
    TextView tvTitle, tvCompany, tvCity, tvField, tvSalary, tvDesc, tvRequirement, tvQualification;
    ImageView imgCompanyLogo;
    ImageButton imgBackButton;
    private String title, company ,photo_logo , city, field, salary, desc, requirements, qualification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_job);

        imgBackButton = findViewById(R.id.imgBackButton);
        tvTitle = findViewById(R.id.tvTitle);
        tvCompany = findViewById(R.id.tvCompany);
        tvCity = findViewById(R.id.tvCity);
        tvField = findViewById(R.id.tvFieldOfWork);
        tvSalary = findViewById(R.id.tvSalary);
        tvDesc = findViewById(R.id.tvDesc);
        tvRequirement = findViewById(R.id.tvRequirement);
        tvQualification = findViewById(R.id.tvQualification);
        imgCompanyLogo = findViewById(R.id.imgCompanyLogo);

        title = getIntent().getStringExtra("JOB_DETAIL_TITLE");
        company = getIntent().getStringExtra("JOB_DETAIL_COMPANY");
        photo_logo = getIntent().getStringExtra("JOB_DETAIL_PHOTO");
        city = getIntent().getStringExtra("JOB_DETAIL_CITY");
        field = getIntent().getStringExtra("JOB_DETAIL_FIELD");
        salary = getIntent().getStringExtra("JOB_DETAIL_SALARY");
        desc = getIntent().getStringExtra("JOB_DETAIL_DESC");
        requirements = getIntent().getStringExtra("JOB_DETAIL_REQUIREMENTS");
        qualification = getIntent().getStringExtra("JOB_DETAIL_QUALIFICATION");

        tvTitle.setText(title);
        tvCompany.setText(company);
        tvCity.setText(city);
        tvField.setText(field);
        tvSalary.setText(salary);
        tvDesc.setText(desc);
        tvRequirement.setText(requirements);
        tvQualification.setText(qualification);

        //link sementara
        String link = photo_logo;
        link = "https://equal.lug-surabaya.com/images/jobs/"+link;
        Log.d("link", link);

        Glide.with(getBaseContext())
                .load(link)
                .into(imgCompanyLogo);

        imgBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
