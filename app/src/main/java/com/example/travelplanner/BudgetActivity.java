package com.example.travelplanner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BudgetActivity extends AppCompatActivity {
    EditText budgetInput;
    Button btnNext;
    ImageView btnBack;

    String name, email, destination, startDate, endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        budgetInput = findViewById(R.id.budgetInput);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        email = i.getStringExtra("email");
        destination = i.getStringExtra("destination");
        startDate = i.getStringExtra("startDate");
        endDate = i.getStringExtra("endDate");

        btnNext.setOnClickListener(v -> {
            String budget = budgetInput.getText().toString().trim();
            if (budget.isEmpty()) {
                Toast.makeText(this, "Please enter a budget", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(BudgetActivity.this, SummaryActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("email", email);
            intent.putExtra("destination", destination);
            intent.putExtra("startDate", startDate);
            intent.putExtra("endDate", endDate);
            intent.putExtra("budget", budget);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        btnBack.setOnClickListener(v -> {
            onBackPressed();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        });
    }
}
