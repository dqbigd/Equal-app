package com.example.equal.Api;

import com.example.equal.Chat.Chat;
import com.example.equal.Chat.ResponseChat;
import com.example.equal.Model.Article;
import com.example.equal.Model.Job;
import com.example.equal.Model.User;
import com.example.equal.Savejob.ResponseSaveJob;
import com.example.equal.Savejob.ResultSaveJob;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiInterface {
    String key_api = "KEY_API_HERE";

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

    @GET(key_api+"/job/")
    Call<List<Job>> getJob();

    @FormUrlEncoded
    @POST(key_api+"/job_user/save")
    Call<ResultSaveJob> setSaveJob(@Field("user_id") Integer user_id,
                                   @Field("job_id") Integer job_id);

    @GET
    Call<ResponseSaveJob> getSaveJob(@Url String url);

    @FormUrlEncoded
    @POST(key_api+"/chat")
    Call<Chat> setChat(@Field("user_id") Integer user_id,
                       @Field("message") String message,
                       @Field("type") Integer type);

    @GET
    Call<ResponseChat> getChat(@Url String url);
}
