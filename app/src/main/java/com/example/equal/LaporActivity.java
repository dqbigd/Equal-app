package com.example.equal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class LaporActivity extends AppCompatActivity {
    ImageButton imgBackButton;
    EditText txtNama, txtLokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapor);

        imgBackButton = findViewById(R.id.imgBackButton);
        txtNama = findViewById(R.id.txtNama);
        txtLokasi = findViewById(R.id.txtAlamat);

        Preferences preferences = new Preferences(this);

        txtNama.setText(preferences.getName());

        imgBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
