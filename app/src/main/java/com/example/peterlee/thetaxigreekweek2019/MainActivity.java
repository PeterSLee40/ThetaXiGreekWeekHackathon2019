package com.example.peterlee.thetaxigreekweek2019;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;
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

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Log.v("Date: ","" + year + "" + month + "" + day);
        updateMealInfoForWeek(year, month, day);
    }

    public void pickDate(MenuItem item) {
        Calendar calander = Calendar.getInstance();
        int curYear = calander.get(Calendar.YEAR);
        int curMonth = calander.get(Calendar.MONTH) + 1;
        int curDay = calander.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                MainActivity.this, curYear, curMonth, curDay);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        updateMealInfoForWeek(i, i1, i2);
    }

    //add meal to firebase.
    protected void addMeal(Meal meal) {
        //Toast.makeText(getApplicationContext(),meal.toString(),Toast.LENGTH_SHORT).show();
        myRef = database.getReference(meal.getDate()
                + Boolean.toString(meal.getMealEnum().isDinner()));
        myRef.setValue(meal);
    }

    public void updateMealInfoForWeek(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        while (cal.get(Calendar.DAY_OF_WEEK) > 1) {
            cal.add(Calendar.DATE, -1);
        }

        cal.add(Calendar.DATE, 1);
        setMeal(getDate(cal) + "0", (TextView) findViewById(R.id.MLTextView));
        setMeal(getDate(cal) + "1", (TextView) findViewById(R.id.MDTextView));
        cal.add(Calendar.DATE, 1);
        setMeal(getDate(cal) + "0", (TextView) findViewById(R.id.TLTextView));
        setMeal(getDate(cal) + "1", (TextView) findViewById(R.id.TDTextView));
        cal.add(Calendar.DATE, 1);
        setMeal(getDate(cal) + "0", (TextView) findViewById(R.id.WLTextView));
        setMeal(getDate(cal) + "1", (TextView) findViewById(R.id.WDTextView));
        cal.add(Calendar.DATE, 1);
        setMeal(getDate(cal) + "0", (TextView) findViewById(R.id.TRLTextView));
        setMeal(getDate(cal) + "1", (TextView) findViewById(R.id.TRDTextView));
        cal.add(Calendar.DATE, 1);
        setMeal(getDate(cal) + "0", (TextView) findViewById(R.id.FLTextView));
    }

    public String getDate(Calendar cal) {
        String newYear = "" + cal.get(Calendar.YEAR);

        String newMonth = "" + cal.get(Calendar.MONTH);
        if (newMonth.length() == 1) {
            newMonth = "0" + newMonth;
        }

        String newDay = "" + cal.get(Calendar.DAY_OF_MONTH);
        if (newDay.length() == 1) {
            newDay = "0" + newDay;
        }
        return newYear + newMonth + newDay;
    }

    //Lunch: April 6, 2019: 201904060 -> "-"
    protected void setMeal(String date, TextView view) {
        Log.v("Getting a meal", date);
        final TextView tv = view;
        myRef = database.getReference(date);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Meal meal = dataSnapshot.getValue(Meal.class);
                if (meal != null) {
                    tv.setText("" + meal.getNumPeopleEating());
                } else {
                    tv.setText("-");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                tv.setText("-");
            }
        };
        final ValueEventListener valueEventListener = myRef.addValueEventListener(postListener);
    }


    public void setupMeals() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
