package com.example.peterlee.thetaxigreekweek2019;

public class Meal {
    private int date;
    private int numPeopleEating;
    private MealEnum mealEnum;
    Meal(int date, int numPeopleEating, MealEnum mealEnum) {
        this.date = date;
        this.numPeopleEating = numPeopleEating;
        this.mealEnum = mealEnum;
    }
    public int getDate() {
        return date;
    }
    public int getNumPeopleEating() {
        return numPeopleEating;
    }
    public MealEnum getMealEnum() {
        return mealEnum;
    }
}
