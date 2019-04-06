package com.example.peterlee.thetaxigreekweek2019;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        Meal meal = new Meal("20190406", 629, MealEnum.FRIDAY_LUNCH);
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
    /*
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
        myRef.addValueEventListener(postListener);
    }
    */
}
