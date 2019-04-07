package com.example.peterlee.thetaxigreekweek2019;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import java.util.Calendar;
import java.util.Date;

import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

//private FirebaseDatabase database;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

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

    }

    protected void addMeal(Meal meal) {
        //add meal to firebase.
    }


    public void setupMeals() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
