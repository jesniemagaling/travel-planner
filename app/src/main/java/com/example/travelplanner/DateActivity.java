package com.example.travelplanner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateActivity extends AppCompatActivity {
    EditText startDateInput, endDateInput;
    Button btnNext;

    String name, email, destination;

    final Calendar startCal = Calendar.getInstance();
    final Calendar endCal = Calendar.getInstance();
    final SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        startDateInput = findViewById(R.id.startDateInput);
        endDateInput = findViewById(R.id.endDateInput);
        btnNext = findViewById(R.id.btnNext);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        email = i.getStringExtra("email");
        destination = i.getStringExtra("destination");

        startDateInput.setOnClickListener(v -> showDatePicker(startCal, startDateInput));
        endDateInput.setOnClickListener(v -> showDatePicker(endCal, endDateInput));

        btnNext.setOnClickListener(v -> {
            String start = startDateInput.getText().toString().trim();
            String end = endDateInput.getText().toString().trim();
            if (start.isEmpty() || end.isEmpty()) {
                Toast.makeText(this, "Please select both dates", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(DateActivity.this, BudgetActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("email", email);
            intent.putExtra("destination", destination);
            intent.putExtra("startDate", start);
            intent.putExtra("endDate", end);
            startActivity(intent);
        });
    }

    private void showDatePicker(final Calendar cal, final EditText target) {
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH);
        int d = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            target.setText(sdf.format(cal.getTime()));
        }, y, m, d);

        dpd.show();
    }
}
