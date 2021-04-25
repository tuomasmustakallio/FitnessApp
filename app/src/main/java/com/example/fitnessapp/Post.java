package com.example.fitnessapp;

import com.google.gson.annotations.SerializedName;

// Makes class for the API
public class Post {

    @SerializedName("body")
    private String quetes;

    private String text;

    private String author;

    public String getText() { return text; }

    public String getAuthor() { return author; }
}
