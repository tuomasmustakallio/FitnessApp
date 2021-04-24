package com.example.fitnessapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    //API KEY
    @GET("posts")
    //METHOD TO EXECUTE GET REQUEST
    Call<List<Post>> getPosts();
}
