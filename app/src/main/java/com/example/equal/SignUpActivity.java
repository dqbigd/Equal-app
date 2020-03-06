package com.example.equal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.equal.Api.ApiClient;
import com.example.equal.Api.ApiInterface;
import com.example.equal.Model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpActivity extends AppCompatActivity {
    Button btnSignUp, btnLogin;
    EditText txtNama, txtEmail, txtPassword;

    final ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnLogin = findViewById(R.id.btnMasuk);
        btnSignUp = findViewById(R.id.btnDaftar);
        txtNama = findViewById(R.id.txtNama);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });

    }

    private void sendData() {
        String nama, email, password;
        Boolean validation = true;

        nama = txtNama.getText().toString();
        email = txtEmail.getText().toString();
        password = txtPassword.getText().toString();

        if (TextUtils.isEmpty(nama)) {
            txtNama.setError("Field tidak boleh kosong");
            validation = false;
        }
        if (TextUtils.isEmpty(email)) {
            txtEmail.setError("Field tidak boleh kosong");
            validation = false;
        }else if (!isValidEmail(email)) {
            txtEmail.setError("Email tidak valid");
            validation = false;
        }
        if (TextUtils.isEmpty(password)) {
            txtPassword.setError("Field tidak boleh kosong");
            validation = false;
        }else if(password.length() < 6) {
            txtPassword.setError("Password minimal 6 karakter");
            validation = false;
        }

        if(validation){
            Call<User> call = apiInterface.registerUser(nama, email, password);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
//                    if (response.isSuccessful()){
////                        response.body();
//                        Toast.makeText(SignUpActivity.this, "SignUp successfully !", Toast.LENGTH_SHORT).show();
////                        Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
////                        startActivity(i);
//                        Log.d("response", response.message());
//
//                    }else {
//                        Toast.makeText(SignUpActivity.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
//                        Log.d("SignUp not response", response.errorBody().toString());
//                    }
                    Toast.makeText(SignUpActivity.this, "SignUp successfully !", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(i);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("SignUp Failure", t.getMessage());
                }
            });
        }


    }

    private boolean isValidEmail(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
