package com.example.fitnessapp;

public class Movement {
    String movementName;
    Integer weight = 0;
    Integer reps = 0;

    public Movement(){

    }

    public Movement(String movementName, Integer weight, Integer reps){
        this.movementName = movementName;
        this.weight = weight;
        this.reps = reps;
    }
    public void setMovementName(String movementName){
        this.movementName = movementName;
    }
    public String getMovementName(){
        return movementName;
    }
    public void setWeight(Integer weight){
        this.weight = weight;
    }
    public Integer getWeight(){
        return weight;
    }
    public void setReps(Integer reps){
        this.reps = reps;
    }
    public Integer getReps(){
        return reps;
    }
    @Override
    public String toString() {
        return movementName;
    }
}
