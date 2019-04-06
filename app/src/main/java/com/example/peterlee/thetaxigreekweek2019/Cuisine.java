package com.example.peterlee.thetaxigreekweek2019;

public enum Cuisine {

    AMERICAN("American"),
    ASIAN("Asian"),
    EUROPEAN("European"),
    LATIN("Latin"),
    MIDDLE_EASTERN("Middle Eastern");

    private final String cuisineName;

    Cuisine(String name) {
        cuisineName = name;
    }

    public String getName() {
        return cuisineName;
    }
}
