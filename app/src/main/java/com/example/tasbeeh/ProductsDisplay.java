package com.example.tasbeeh;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProductsDisplay extends AppCompatActivity {

    // Declare UI elements
    private TextView counterDisplay;
    private Button incrementButton;
    private Button resetButton;

    // Counter variable
    private int count = 0;

    // Maximum count before reset
    private final int MAX_COUNT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_display);

        // Initialize UI elements
        initializeViews();

        // Set up button click listeners
        setupButtonListeners();

        // Restore previous count if activity was recreated
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count", 0);
            updateDisplay();
        }
    }

    // Initialize views from layout
    private void initializeViews() {
        counterDisplay = findViewById(R.id.textView);
        incrementButton = findViewById(R.id.tap);
        resetButton = findViewById(R.id.reset);
    }

    // Set up click listeners for buttons
    private void setupButtonListeners() {
        incrementButton.setOnClickListener(v -> incrementCounter());
        resetButton.setOnClickListener(v -> resetCounter());
    }

    // Increment the counter
    private void incrementCounter() {
        count++;

        // Reset counter if it exceeds MAX_COUNT
        if (count > MAX_COUNT) {
            count = 0;
            Toast.makeText(this, "Counter reset to 0", Toast.LENGTH_SHORT).show();
        }

        updateDisplay();
    }

    // Reset the counter to 0
    private void resetCounter() {
        count = 0;
        updateDisplay();
    }

    // Update the counter display
    private void updateDisplay() {
        counterDisplay.setText(String.valueOf(count));
    }

    // Save the current count when the activity might be destroyed
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
    }
}