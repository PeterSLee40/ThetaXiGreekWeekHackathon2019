package com.example.peterlee.thetaxigreekweek2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.peterlee.thetaxigreekweek2019.MealEnum.MONDAY_LUNCH;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
}


    //add meal to firebase.
    protected void addMeal(String date, Meal meal) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(date);
        myRef.setValue(meal);

    }
}
