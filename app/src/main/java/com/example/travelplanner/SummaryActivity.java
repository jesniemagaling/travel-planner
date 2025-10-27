package com.example.travelplanner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SummaryActivity extends AppCompatActivity {
    TextView summaryText;
    Button btnFinish;

    String name, email, destination, startDate, endDate, budget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        summaryText = findViewById(R.id.summaryText);
        btnFinish = findViewById(R.id.btnFinish);

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        destination = getIntent().getStringExtra("destination");
        startDate = getIntent().getStringExtra("startDate");
        endDate = getIntent().getStringExtra("endDate");
        budget = getIntent().getStringExtra("budget");

        String summary = "Traveler: " + name + "\n"
                + "Email: " + email + "\n"
                + "Destination: " + destination + "\n"
                + "Travel Dates: " + startDate + " to " + endDate + "\n"
                + "Estimated Budget: ₱" + budget;

        summaryText.setText(summary);

        btnFinish.setOnClickListener(v -> {
            createPdf(summary);
        });
    }

    private void createPdf(String summary) {
        // Save to Downloads folder
        File pdfFile = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "TravelSummary.pdf");

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();
            document.add(new Paragraph("Travel Planner Summary\n\n"));
            document.add(new Paragraph(summary));
            document.close();

            Toast.makeText(this, "PDF saved to: " + pdfFile.getAbsolutePath(), Toast.LENGTH_LONG).show();

            // Go back to LoginActivity after saving PDF
            Intent intent = new Intent(SummaryActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save PDF", Toast.LENGTH_SHORT).show();
        }
    }
}
