package com.example.fitnessapp;

public class Person {

    String username;
    String password;
    int age = 0;
    String gender = "";
    int height = 0;
    int weight = 0;


    public Person(){

    }

    public Person(int age, String gender, int height, int weight){
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;

    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getUsername(){return username;}
    public void setUsername(String username){
        this.username = username;
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
