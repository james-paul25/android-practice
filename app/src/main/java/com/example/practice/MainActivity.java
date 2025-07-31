package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button counter;
    private Button displayBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        counter = findViewById(R.id.counterBtn);
        displayBtn = findViewById(R.id.displayBtn);

        counter.setOnClickListener(v -> {
            Intent intent = new Intent(this, CounterActivity.class);
            startActivity(intent);
            finish();
        });

        displayBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, DisplayActivity.class);
            startActivity(intent);
            finish();
        });
    }
}