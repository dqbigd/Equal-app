package com.example.equal;

import com.example.equal.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("register")
    Call<User> registerUser(@Field("name") String name,
                            @Field("email") String email,
                            @Field("password") String password);

    @FormUrlEncoded
    @POST("login")
    Call<User> loginUser(@Field("email") String email,
                         @Field("password") String password);


}
