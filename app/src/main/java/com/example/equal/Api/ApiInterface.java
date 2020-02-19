package com.example.equal.Api;

import com.example.equal.Model.Article;
import com.example.equal.Model.Job;
import com.example.equal.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiInterface {
    String key_api = "PdSgVkYp3s6v9y$B";

    @FormUrlEncoded
    @POST("register")
    Call<User> registerUser(@Field("name") String name,
                            @Field("email") String email,
                            @Field("password") String password);

    @FormUrlEncoded
    @POST("login")
    Call<User> loginUser(@Field("email") String email,
                         @Field("password") String password);

    @GET(key_api+"/article/")
    Call<List<Article>> getArticle();

    @GET
    Call<Job> getJob(@Url String url);
}
