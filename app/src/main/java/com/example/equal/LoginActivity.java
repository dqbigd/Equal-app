package com.example.equal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnSignUp;
    EditText txtEmail, txtPassword;

    final ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnMasuk);
        btnSignUp = findViewById(R.id.btnDaftar);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);

        Preferences preferences = new Preferences(this);

        if (preferences.getLoggedIn()){
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SignUpActivity.class));
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();

//                startActivity(new Intent(getBaseContext(), MainActivity.class));
//                finish();
            }
        });
    }

    private void setData(){
        final Preferences preferences = new Preferences(this);
        String email, password;

        email = txtEmail.getText().toString();
        password = txtPassword.getText().toString();

        Call<User> call = apiInterface.loginUser(email, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    response.body();
                    Integer id = response.body().getId();
                    Log.d("RETROFIT: success", String.valueOf(id));
                    preferences.setLoggedIn(true, id);
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("RETROFIT: fail", t.getMessage());
            }
        });
    }
}
