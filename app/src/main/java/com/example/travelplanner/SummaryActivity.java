package com.example.travelplanner;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SummaryActivity extends AppCompatActivity {

    TextView summaryText;
    Button btnFinish;
    ImageView btnBack;

    String name, email, destination, startDate, endDate, budget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        summaryText = findViewById(R.id.summaryText);
        btnFinish = findViewById(R.id.btnFinish);
        btnBack = findViewById(R.id.btnBack);

        // Retrieve data
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

        // Save PDF button
        btnFinish.setOnClickListener(v -> createPdf(summary));

        // Back button
        btnBack.setOnClickListener(v -> onBackPressed());
    }

    private void createPdf(String summary) {
        String fileName = "TravelSummary_" + System.currentTimeMillis() + ".pdf";
        OutputStream outputStream;

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Downloads.DISPLAY_NAME, fileName);
                values.put(MediaStore.Downloads.MIME_TYPE, "application/pdf");
                values.put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS);

                Uri uri = getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);
                if (uri == null) {
                    Toast.makeText(this, "Failed to access Downloads", Toast.LENGTH_SHORT).show();
                    return;
                }

                outputStream = getContentResolver().openOutputStream(uri);
            } else {
                File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                if (!downloadsDir.exists()) downloadsDir.mkdirs();
                File pdfFile = new File(downloadsDir, fileName);
                outputStream = new FileOutputStream(pdfFile);
            }

            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();
            document.add(new Paragraph("Travel Planner Summary\n\n"));
            document.add(new Paragraph("Traveler: " + name));
            document.add(new Paragraph("Email: " + email));
            document.add(new Paragraph("Destination: " + destination));
            document.add(new Paragraph("Travel Dates: " + startDate + " to " + endDate));
            document.add(new Paragraph("Estimated Budget: ₱" + budget));
            document.close();

            outputStream.close();

            Toast.makeText(this, "PDF saved to Downloads folder.", Toast.LENGTH_LONG).show();

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
