package com.example.travelplanner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DestinationActivity extends AppCompatActivity {
    EditText destinationInput;
    Button btnNext;

    String name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        destinationInput = findViewById(R.id.destinationInput);
        btnNext = findViewById(R.id.btnNext);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        email = i.getStringExtra("email");

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
            startActivity(intent);
        });
    }
}
