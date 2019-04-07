package com.example.peterlee.thetaxigreekweek2019;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Calendar;
import java.util.Date;

import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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

    Meal[] curMeals;
    int curYear;
    int curMonth;
    int curDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();

        createTestData();

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        updateMealInfoForWeek(year, month, day);
        setKeyString();
    }
    public void createTestData() {
        Meal meal1 = new Meal("20190401", 40,
                MealEnum.MONDAY_LUNCH, Cuisine.AMERICAN);
        Meal meal2 = new Meal("20190401", 60,
                MealEnum.MONDAY_DINNER, Cuisine.AMERICAN);
        Meal meal3 = new Meal("20190402", 45,
                MealEnum.TUESDAY_LUNCH, Cuisine.LATIN);
        Meal meal4 = new Meal("20190402", 55,
                MealEnum.TUESDAY_DINNER, Cuisine.ASIAN);
        Meal meal5 = new Meal("20190403", 50,
                MealEnum.WEDNESDAY_LUNCH, Cuisine.LATIN);
        Meal meal6 = new Meal("20190403", 70,
                MealEnum.WEDNESDAY_DINNER, Cuisine.AMERICAN);
        Meal meal7 = new Meal("20190404", 40,
                MealEnum.THURSDAY_LUNCH, Cuisine.AMERICAN);
        Meal meal8 = new Meal("20190404", 20,
                MealEnum.THURSDAY_DINNER, Cuisine.LATIN);
        Meal meal9 = new Meal("20190405", 40,
                MealEnum.FRIDAY_LUNCH, Cuisine.AMERICAN);
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

    public void setKeyString() {
        TextView keyTextView = findViewById(R.id.keyTextView);
        keyTextView.setText(Html.fromHtml("<font color=\"#F44336\">American</font> | " +
                "<font color='#673AB7'>Asian</font> | <font color='#03A9F4'>Latin</font>"));
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

    public void pickCuisines(MenuItem item) {
        final String[] cuisines = {Cuisine.AMERICAN.getName(), Cuisine.ASIAN.getName(),
                Cuisine.LATIN.getName()};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cuisines);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        LayoutInflater factory = LayoutInflater.from(this);
        final View SCDialogView = factory.inflate(R.layout.pick_cuisines, null);
        final AlertDialog SCDialog = new AlertDialog.Builder(this).create();
        SCDialog.setView(SCDialogView);

        final Spinner[] spinners = new Spinner[9];
        spinners[0] = (Spinner) SCDialogView.findViewById(R.id.MLSpinner);
        spinners[1] = (Spinner) SCDialogView.findViewById(R.id.MDSpinner);
        spinners[2] = (Spinner) SCDialogView.findViewById(R.id.TLSpinner);
        spinners[3] = (Spinner) SCDialogView.findViewById(R.id.TDSpinner);
        spinners[4] = (Spinner) SCDialogView.findViewById(R.id.WLSpinner);
        spinners[5] = (Spinner) SCDialogView.findViewById(R.id.WDSpinner);
        spinners[6] = (Spinner) SCDialogView.findViewById(R.id.TRLSpinner);
        spinners[7] = (Spinner) SCDialogView.findViewById(R.id.TRDSpinner);
        spinners[8] = (Spinner) SCDialogView.findViewById(R.id.FLSpinner);

        SCDialogView.findViewById(R.id.ConfirmationButton).setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < spinners.length; i++) {
                    if (curMeals[i] != null) {
                        if (curMeals[i].getCuisine().getName() !=
                                cuisines[spinners[i].getSelectedItemPosition()]) {
                            curMeals[i].setCuisine(Cuisine.fromString(
                                    cuisines[spinners[i].getSelectedItemPosition()]));
                            addMeal(curMeals[i]);
                        }
                    } else {
                        int extraDays = (i / 2) + 1;
                        Calendar cal = Calendar.getInstance();
                        cal.set(curYear, curMonth, curDay);
                        cal.add(Calendar.DATE, extraDays);

                        Meal curMeal = new Meal(getDate(cal), (int) (Math.random() * 20) + 40, MealEnum.fromInt(i),
                                Cuisine.fromString(
                                        cuisines[spinners[i].getSelectedItemPosition()]));
                        addMeal(curMeal);
                        curMeals[i] = curMeal;
                    }
                }
                SCDialog.dismiss();
            }
        });

        for (int i = 0; i < spinners.length; i++) {
            setupSpinner(spinners[i], adapter, i);
        }

        SCDialog.show();
    }

    public void setupSpinner(Spinner spinner, ArrayAdapter<String> adapter, int mealLoc) {
        spinner.setAdapter(adapter);
        if (curMeals != null && curMeals[mealLoc] != null) {
            if (curMeals[mealLoc].getCuisine() == Cuisine.AMERICAN) {
                spinner.setSelection(0);
            } else if (curMeals[mealLoc].getCuisine() == Cuisine.ASIAN) {
                spinner.setSelection(1);
            } else {
                spinner.setSelection(2);
            }
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        updateMealInfoForWeek(i, i1, i2);
    }

    protected String bool2str(Boolean b) {
        return b ? "1" : "0";
    }

    protected void addMeal(Meal meal) {
        myRef = database.getReference(meal.getDate()
                + bool2str(meal.getMealEnum().isDinner()));
        myRef.setValue(meal);
    }

    public void updateMealInfoForWeek(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        while (cal.get(Calendar.DAY_OF_WEEK) != 1) {
            cal.add(Calendar.DATE, -1);
        }

        curYear = cal.get(Calendar.YEAR);
        curMonth = cal.get(Calendar.MONTH);
        curDay = cal.get(Calendar.DAY_OF_MONTH);

        TextView curWeek = findViewById(R.id.weekTextView);

        curMeals = new Meal[9];
        cal.add(Calendar.DATE, 1);
        String curWeekStr = "" + (cal.get(Calendar.MONTH) + 1) + "/"
                + (cal.get(Calendar.DAY_OF_MONTH)) + " - ";
        setMeal(getDate(cal) + "0", (TextView) findViewById(R.id.MLTextView), 0);
        setMeal(getDate(cal) + "1", (TextView) findViewById(R.id.MDTextView), 1);
        cal.add(Calendar.DATE, 1);
        setMeal(getDate(cal) + "0", (TextView) findViewById(R.id.TLTextView), 2);
        setMeal(getDate(cal) + "1", (TextView) findViewById(R.id.TDTextView), 3);
        cal.add(Calendar.DATE, 1);
        setMeal(getDate(cal) + "0", (TextView) findViewById(R.id.WLTextView), 4);
        setMeal(getDate(cal) + "1", (TextView) findViewById(R.id.WDTextView), 5);
        cal.add(Calendar.DATE, 1);
        setMeal(getDate(cal) + "0", (TextView) findViewById(R.id.TRLTextView), 6);
        setMeal(getDate(cal) + "1", (TextView) findViewById(R.id.TRDTextView), 7);
        cal.add(Calendar.DATE, 1);
        setMeal(getDate(cal) + "0", (TextView) findViewById(R.id.FLTextView), 8);
        curWeekStr = curWeekStr + (cal.get(Calendar.MONTH) + 1) + "/"
                + (cal.get(Calendar.DAY_OF_MONTH));
        curWeek.setText(curWeekStr);
    }

    public String getDate(Calendar cal) {
        String newYear = "" + cal.get(Calendar.YEAR);

        String newMonth = "" + (cal.get(Calendar.MONTH) + 1);
        if (newMonth.length() == 1) {
            newMonth = "0" + newMonth;
        }

        String newDay = "" + (cal.get(Calendar.DAY_OF_MONTH));
        if (newDay.length() == 1) {
            newDay = "0" + newDay;
        }
        return newYear + newMonth + newDay;
    }

    protected void setMeal(String date, TextView view, int loc) {
        final String fDate = date;
        final TextView tv = view;
        final int mealLoc = loc;
        myRef = database.getReference(date);
        ValueEventListener postListener = new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Meal meal = dataSnapshot.getValue(Meal.class);
                if (meal != null) {
                    if (curMeals != null) {
                        curMeals[mealLoc] = meal;
                    }

                    if (meal.getNumPeopleEating() != -1) {
                        tv.setText("" + meal.getNumPeopleEating());
                    } else {
                        tv.setText("?");
                    }
                    String date = fDate.substring(0, fDate.length() - 1);
                    Calendar calander = Calendar.getInstance();
                    int curYear = calander.get(Calendar.YEAR);
                    int curMonth = calander.get(Calendar.MONTH);
                    int curDay = calander.get(Calendar.DAY_OF_MONTH);
                    String newDate = "" + curYear + "" + curMonth + "" + curDay;
                    if (date.compareTo(newDate) <= 0) {
                        tv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //Let them set the data and submit it
                                AlertDialog.Builder alert = new AlertDialog.Builder(
                                        MainActivity.this);
                                alert.setTitle("Set # of Attendees at Meal");
                                final EditText input = new EditText(MainActivity.this);
                                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                                input.setRawInputType(Configuration.KEYBOARD_12KEY);
                                alert.setView(input);
                                alert.setPositiveButton("Submit",
                                        new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        curMeals[mealLoc].setNumPeopleEating(
                                                Integer.parseInt(input.getText().toString()));
                                        addMeal(curMeals[mealLoc]);
                                        tv.setText(input.getText().toString());
                                    }
                                });
                                alert.setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {}});
                                alert.show();
                            }
                        });
                    } else {
                        tv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //Don't do nothing here
                            }
                        });
                    }

                    switch (meal.getCuisine()) {
                        case ASIAN:
                            //Set background
                            tv.setBackground(ContextCompat.getDrawable(MainActivity.this,
                                    R.drawable.circle_asian));
                            break;
                        case AMERICAN:
                            tv.setBackground(ContextCompat.getDrawable(MainActivity.this,
                                    R.drawable.circle_american));
                            break;
                        case LATIN:
                            tv.setBackground(ContextCompat.getDrawable(MainActivity.this,
                                    R.drawable.circle_latin));
                            break;
                    }
                } else {
                    tv.setText("-");
                    tv.setBackground(ContextCompat.getDrawable(MainActivity.this,
                            R.drawable.circle_blank));
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Don't do nothing here
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                tv.setText("-");
                tv.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.circle_blank));
            }
        };
        final ValueEventListener valueEventListener = myRef.addValueEventListener(postListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
