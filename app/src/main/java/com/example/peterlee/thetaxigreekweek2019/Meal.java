package com.example.peterlee.thetaxigreekweek2019;

public class Meal {
    private String date;
    private int numPeopleEating;
    private MealEnum mealEnum;
    private Cuisine cuisine;

    Meal() {
        date = "";
        numPeopleEating = -1;
        mealEnum = MealEnum.MONDAY_LUNCH;
        cuisine = Cuisine.AMERICAN;
    }

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

    public void setCuisine(Cuisine newCuisine) {
        cuisine = newCuisine;
    }

    public void setNumPeopleEating(int numPeopleEating) {
        this.numPeopleEating = numPeopleEating;
    }

    @Override
    public String toString() {
        return ("date: " + date +  "numPplEating" +
                Integer.toString(numPeopleEating) + " " + mealEnum.toString());
    }
    //lunch, time
    public String toJson(){
        String meal = " \"meal\": ";
        if (mealEnum.isLunch()) {
            meal += "\"{1}\"";
        } else if (!mealEnum.isLunch()){
            meal += "\"{0}\"";
        }
        String time = " \"meal\": ";
        if (!mealEnum.isLunch() & mealEnum.isMWF()){
            time += "\"{1}\"";
        } else if (!mealEnum.isLunch() & !mealEnum.isMWF()){
            time += "\"{0}\"";
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
        return "{" + meal + time + cuisine + numberOfPplEating +"}";
    }
}
