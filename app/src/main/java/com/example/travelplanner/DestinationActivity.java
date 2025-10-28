package com.example.travelplanner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DestinationActivity extends AppCompatActivity {
    EditText destinationInput;
    Button btnNext;
    ImageView btnBack;

    String name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination); // make sure this matches your XML filename

        destinationInput = findViewById(R.id.destinationInput);
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack); // this should now work

        // Retrieve data
        Intent i = getIntent();
        name = i.getStringExtra("name");
        email = i.getStringExtra("email");

        // Next button
        btnNext.setOnClickListener(v -> {
            String dest = destinationInput.getText().toString().trim();
            if (dest.isEmpty()) {
                Toast.makeText(this, "Please enter a destination", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(DestinationActivity.this, DateActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("email", email);
            intent.putExtra("destination", dest);
            Toast.makeText(this, "Name: " + name + ", Email: " + email, Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });

        // Back button
        btnBack.setOnClickListener(v -> {
            onBackPressed();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        });
    }
}
