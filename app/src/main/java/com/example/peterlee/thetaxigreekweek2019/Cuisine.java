package com.example.peterlee.thetaxigreekweek2019;

import android.graphics.Color;

public enum Cuisine {

    AMERICAN("American", "#D64933"),
    ASIAN("Asian", "#E9724C"),
    LATIN("Latin", "#56A3A6");

    private final String cuisineName;
    private final String cuisineColor;

    Cuisine(String name, String color) {
        cuisineName = name;
        cuisineColor = color;
    }

    public String getName() {
        return cuisineName;
    }

    public static Cuisine getEnum(String s){
        if(AMERICAN.name().equals(s)){
            return AMERICAN;
        }else if(ASIAN.name().equals(s)){
            return ASIAN;
        }else if(LATIN.name().equals(s)){
            return LATIN;
        }
        throw new IllegalArgumentException("No Enum specified for this string");
    }

    public int getColor() {
        return Color.parseColor(cuisineColor);
    }

}