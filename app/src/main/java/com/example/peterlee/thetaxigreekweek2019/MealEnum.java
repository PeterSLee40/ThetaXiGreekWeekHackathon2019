package com.example.peterlee.thetaxigreekweek2019;

public enum MealEnum {

    MONDAY_LUNCH("Monday Lunch", true, 0),
    MONDAY_DINNER("Monday Dinner", false, 0),
    TUESDAY_LUNCH("Tuesday Lunch", true, 1),
    TUESDAY_DINNER("Tuesday Dinner", false, 1),
    WEDNESDAY_LUNCH("Wednesday Lunch", true, 2),
    WEDNESDAY_DINNER("Wednesday Dinner", false, 2),
    THURSDAY_LUNCH("Thursday Lunch", true, 3),
    THURSDAY_DINNER("Thursday Dinner", false, 3),
    FRIDAY_LUNCH("Friday Lunch", true, 4);
    private final String mealName;
    private final boolean mealIsLunch;
    private final int mealDayOfWeek;

    MealEnum(String name, boolean isLunch, int dayOfWeek) {
        mealName = name;
        mealIsLunch = isLunch;
        mealDayOfWeek = dayOfWeek;
    }

    public String getName() {
        return mealName;
    }

    public boolean isLunch() {
        return mealIsLunch;
    }

    public boolean isDinner() {
        return !mealIsLunch;
    }

    public boolean isMWF() {
        return mealDayOfWeek % 2 == 0;
    }

    public int getDayOfWeek() {
        return mealDayOfWeek;
    }

    public static MealEnum fromInt(int value) {
        if (value == 0) {
            return MONDAY_LUNCH;
        } else if (value == 1) {
            return MONDAY_DINNER;
        } else if (value == 2) {
            return TUESDAY_LUNCH;
        } else if (value == 3) {
            return TUESDAY_DINNER;
        } else if (value == 4) {
            return WEDNESDAY_LUNCH;
        } else if (value == 5) {
            return WEDNESDAY_DINNER;
        } else if (value == 6) {
            return THURSDAY_LUNCH;
        } else if (value == 7) {
            return THURSDAY_DINNER;
        } else {
            return FRIDAY_LUNCH;
        }
    }
}

