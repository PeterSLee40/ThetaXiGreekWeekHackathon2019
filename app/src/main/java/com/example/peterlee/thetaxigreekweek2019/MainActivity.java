package com.example.peterlee.thetaxigreekweek2019;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Calendar;

import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        createFakeData();
    }
    public void createFakeData() {
        Meal meal1 = new Meal("20190401", 40, MealEnum.MONDAY_LUNCH, Cuisine.AMERICAN);
        Meal meal2 = new Meal("20190401", 60, MealEnum.MONDAY_DINNER, Cuisine.AMERICAN);
        Meal meal3 = new Meal("20190402", 45, MealEnum.TUESDAY_LUNCH, Cuisine.LATIN);
        Meal meal4 = new Meal("20190402", 55, MealEnum.TUESDAY_DINNER, Cuisine.MIDDLE_EASTERN);
        Meal meal5 = new Meal("20190403", 50, MealEnum.WEDNESDAY_LUNCH, Cuisine.LATIN);
        Meal meal6 = new Meal("20190403", 70, MealEnum.WEDNESDAY_DINNER, Cuisine.EUROPEAN);
        Meal meal7 = new Meal("20190404", 40, MealEnum.THURSDAY_LUNCH, Cuisine.AMERICAN);
        Meal meal8 = new Meal("20190404", 20, MealEnum.THURSDAY_DINNER, Cuisine.LATIN);
        Meal meal9 = new Meal("20190405", 40, MealEnum.FRIDAY_LUNCH, Cuisine.EUROPEAN);
        addMeal(meal1);
        addMeal(meal2);
        addMeal(meal3);
        addMeal(meal4);
        addMeal(meal5);
        addMeal(meal6);
        addMeal(meal7);
        addMeal(meal8);
        addMeal(meal9);
    }

    public void pickDate(MenuItem item) {
        Calendar calander = Calendar.getInstance();
        int curYear = calander.get(Calendar.YEAR);
        int curMonth = calander.get(Calendar.MONTH);
        int curDay = calander.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                MainActivity.this, curYear, curMonth, curDay);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        database = FirebaseDatabase.getInstance();
        Meal meal = new Meal("20190406", 629, MealEnum.FRIDAY_LUNCH, Cuisine.AMERICAN);
        addMeal(meal);
        //getMeal("201904061");

    }

    protected String bool2str(Boolean b) {
        return b ? "1" : "0";
    }

    //add meal to firebase.
    protected void addMeal(Meal meal) {
        //Toast.makeText(getApplicationContext(),meal.toString(),Toast.LENGTH_SHORT).show();
        myRef = database.getReference(meal.getDate()
                + bool2str(meal.getMealEnum().isDinner()));
        myRef.setValue(meal);
    }

    protected void getMeal(String date) {
        myRef = database.getReference(date);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Meal meal = dataSnapshot.getValue(Meal.class);
                Toast.makeText(getApplicationContext(),meal.toString(),Toast.LENGTH_SHORT).show();

                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.d("loadPost:onCancelled");
                // ...
            }
        };
        final ValueEventListener valueEventListener = myRef.addValueEventListener(postListener);
    }

/*
    public void setupMeals() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }*/
}
