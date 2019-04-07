package com.example.peterlee.thetaxigreekweek2019;

public class Meal {
    private String date;
    private int numPeopleEating;
    private MealEnum mealEnum;
    private Cuisine cuisine;

    Meal(String date, int numPeopleEating, MealEnum mealEnum, Cuisine cuisine) {
        this.date = date;
        this.numPeopleEating = numPeopleEating;
        this.mealEnum = mealEnum;
        this.cuisine = cuisine;
    }
    Meal(){
        date = "";
        numPeopleEating = 0;
        mealEnum = MealEnum.MONDAY_LUNCH;
        cuisine = Cuisine.AMERICAN;
    }
    public String getDate() {
        return date;
    }
    public Cuisine getCuisine() {
        return cuisine;
    }
    public int getNumPeopleEating() {
        return numPeopleEating;
    }
    public MealEnum getMealEnum() {
        return mealEnum;
    }

    @Override
    public String toString() {
        return ("date: " + date +  "numPplEating: " + Integer.toString(numPeopleEating) +
                "Cuisine: " + cuisine.toString() + " " + mealEnum.toString());
    }
}
