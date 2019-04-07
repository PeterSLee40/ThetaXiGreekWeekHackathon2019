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

    public int getColor() {
        return Color.parseColor(cuisineColor);
    }

}