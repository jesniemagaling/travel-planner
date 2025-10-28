package com.example.travelplanner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class DateActivity extends AppCompatActivity {

    CalendarView calendarView;
    Button btnNextDate;
    ImageView btnBack;
    String name, email, destination;
    String startDate = "", endDate = "";
    boolean isStartDateSelected = false; // to track first and second click

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        calendarView = findViewById(R.id.calendarView);
        btnNextDate = findViewById(R.id.btnNextDate);
        btnBack = findViewById(R.id.btnBack);

        // Get data from previous screen
        Intent i = getIntent();
        name = i.getStringExtra("name");
        email = i.getStringExtra("email");
        destination = i.getStringExtra("destination");

        // Handle date selection
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String chosenDate = (month + 1) + "/" + dayOfMonth + "/" + year;

            if (!isStartDateSelected) {
                startDate = chosenDate;
                endDate = ""; // clear old end date
                isStartDateSelected = true;
                Toast.makeText(this, "Start date set: " + startDate, Toast.LENGTH_SHORT).show();
            } else {
                endDate = chosenDate;
                isStartDateSelected = false;
                Toast.makeText(this, "End date set: " + endDate, Toast.LENGTH_SHORT).show();
            }
        });

        // Back button
        btnBack.setOnClickListener(v -> {
            onBackPressed();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        });

        // Next button
        btnNextDate.setOnClickListener(v -> {
            if (startDate.isEmpty() || endDate.isEmpty()) {
                Toast.makeText(this, "Please select both start and end dates", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(DateActivity.this, BudgetActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("email", email);
            intent.putExtra("destination", destination);
            intent.putExtra("startDate", startDate);
            intent.putExtra("endDate", endDate);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }
}
