package com.example.fitnessapp;

public class Database {

    int id;
    int age;
    String gender;
    int height;
    int weight;


    public Database(){

    }

    public Database(int id,int age, String gender, int height, int weight){
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getAge(){
            return age;
    }

    public void setAge(int age){
            this.age = age;
    }

    public String getGender(){
            return gender;
    }

    public void setGender(String gender){
            this.gender = gender;
    }

    public int getHeight(){
            return height;
    }

    public void setHeight(int height){
            this.height = height;
    }

    public int getWeight(){
        return weight;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }


}
