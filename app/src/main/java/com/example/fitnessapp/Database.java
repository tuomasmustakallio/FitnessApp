package com.example.fitnessapp;

public class Database {

        int age;
        String gender;
        String height;
        int weight;

        public DataBase(){

    }

    public DataBase(int age, String gender, String height, int weight){
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;

    }

    public int getAge(){
            return age;
    }

    public int setAge(int age){
        return age;
    }

    public String getGender(){
            return gender;
    }

    public String setGender(String gender){
        this.gender = gender;
    }



}
