package com.example.equal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class LaporActivity extends AppCompatActivity {
    ImageButton imgBackButton;
    EditText txtNama, txtLokasi, txtKeteragan;
    LinearLayout lyLapor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapor);

        imgBackButton = findViewById(R.id.imgBackButton);
        txtNama = findViewById(R.id.txtNama);
        txtLokasi = findViewById(R.id.txtAlamat);
        txtKeteragan = findViewById(R.id.txtKronologi);
        lyLapor = findViewById(R.id.lyLapor);

        Preferences preferences = new Preferences(this);

        txtNama.setText(preferences.getName());

        imgBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lyLapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendtoEmail();
            }
        });
    }

    private void SendtoEmail() {
        String nama, lokasi, keterangan;
        Boolean validation = true;

        nama = txtNama.getText().toString();
        lokasi = txtLokasi.getText().toString();
        keterangan = txtKeteragan.getText().toString();


        if (TextUtils.isEmpty(nama)) {
            txtNama.setError("Field tidak boleh kosong");
            validation = false;
        }
        if (TextUtils.isEmpty(lokasi)) {
            txtLokasi.setError("Field tidak boleh kosong");
            validation = false;
        }
        if (TextUtils.isEmpty(keterangan)) {
            keterangan = " - ";
        }

        if(validation){
            String recipients = "mabes@polri.go.id";
            String subject = "Laporan KDRT ";
            String message = "Nama : "+nama+"/nLokasi : "+lokasi+"/nKeterangan / kronologi : "+keterangan;

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, message);

            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "Choose an email client"));
        }
    }
}
