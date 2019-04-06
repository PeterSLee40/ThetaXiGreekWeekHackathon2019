package com.example.peterlee.thetaxigreekweek2019;

public class Meal {
    private String date;
    private int numPeopleEating;
    private MealEnum mealEnum;

    Meal(String date, int numPeopleEating, MealEnum mealEnum) {
        this.date = date;
        this.numPeopleEating = numPeopleEating;
        this.mealEnum = mealEnum;
    }
    Meal(){
        date = "";
        numPeopleEating = 0;
        mealEnum = MealEnum.MONDAY_LUNCH;
    }
    public String getDate() {
        return date;
    }
    public int getNumPeopleEating() {
        return numPeopleEating;
    }
    public MealEnum getMealEnum() {
        return mealEnum;
    }

    @Override
    public String toString() {
        return ("date: " + date +  "numPplEating" +
                Integer.toString(numPeopleEating) + " " + mealEnum.toString());
    }
}
