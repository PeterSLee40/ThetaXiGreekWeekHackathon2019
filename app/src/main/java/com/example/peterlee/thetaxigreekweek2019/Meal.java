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

    public String getDate() {
        return date;
    }
    public int getNumPeopleEating() {
        return numPeopleEating;
    }
    public MealEnum getMealEnum() {
        return mealEnum;
    }
    public Cuisine getCuisine() {
        return cuisine;
    }

    @Override
    public String toString() {
        return ("date: " + date +  "numPplEating" +
                Integer.toString(numPeopleEating) + " " + mealEnum.toString());
    }
    //lunch, time
    public String toJson(){
        String mealtime = " \"mealtime\": ";
        if (mealEnum.isLunch() & mealEnum.isMWF()) {
            mealtime += "\"{0,0,0,1}\"";
        } else if (mealEnum.isLunch() & !mealEnum.isMWF()){
            mealtime += "\"{0,0,1,0}\"";
        } else if (!mealEnum.isLunch() & mealEnum.isMWF()){
            mealtime += "\"{0,1,0,0}\"";
        } else if (!mealEnum.isLunch() & !mealEnum.isMWF()){
            mealtime += "\"{1,0,0,0}\"";
        }
        String cuisineJson = "\"Cuisine\" :";
        if (cuisine.equals("American")) {
            cuisineJson += "\"{0,0,0,0,1}\"";
        } else if (cuisine.equals("Asian")) {
            cuisineJson += "\"{0,0,0,1,0}\"";
        } else if (cuisine.equals("European")) {
            cuisineJson += "\"{0,0,1,0,0}\"";
        } else if (cuisine.equals("Latin")) {
            cuisineJson += "\"{0,1,0,0,0}\"";
        } else if (cuisine.equals("Middle Eastern")) {
            cuisineJson += "\"{1,0,0,0,0}\"";
        }
        String numberOfPplEating = "\"numberOfPplEating\" :";
        numberOfPplEating += numPeopleEating;
        return "{" + mealtime + cuisine + numberOfPplEating +"}";
    }
}
