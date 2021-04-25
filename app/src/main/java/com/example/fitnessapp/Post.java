package com.example.fitnessapp;

import com.google.gson.annotations.SerializedName;

// Makes class for the API
public class Post {

    @SerializedName("body")

    private String text;


    public String getText() { return text; }


}
