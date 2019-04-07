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
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");

        //myRef.setValue("Hello, my dudes!");
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

    //add meal to firebase.
    protected void addMeal(Meal meal) {
        //Toast.makeText(getApplicationContext(),meal.toString(),Toast.LENGTH_SHORT).show();
        myRef = database.getReference(meal.getDate()
                + Boolean.toString(meal.getMealEnum().isDinner()));
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
