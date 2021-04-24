package com.example.fitnessapp;

public class Person  {

    String username;
    String password;
    String age = "";
    String gender = "";
    String height = "";
    String weight = "";


    public Person(){

    }

    public Person(String age, String gender, String height, String weight){
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

    public String getAge(){
            return age;
    }

    public void setAge(String age){
            this.age = age;
    }

    public String getGender(){
            return gender;
    }

    public void setGender(String gender){
            this.gender = gender;
    }

    public String getHeight(){
            return height;
    }

    public void setHeight(String height){
            this.height = height;
    }

    public String getWeight(){
        return weight;
    }

    public void setWeight(String weight){ this.weight = weight; }


}
