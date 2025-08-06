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
    private Button ttsBtn;
    private Button sttBtn;
    private Button speakTTBtn;
    private Button todoBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        counter = findViewById(R.id.counterBtn);
        displayBtn = findViewById(R.id.displayBtn);
        ttsBtn = findViewById(R.id.ttsBtn);
        sttBtn = findViewById(R.id.sttBtn);
        speakTTBtn = findViewById(R.id.speakTTBtn);
        todoBtn = findViewById(R.id.todoBtn);

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
        ttsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, TextToSpeechActivity.class);
            startActivity(intent);
            finish();
        });
        sttBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, SpeechToTextActivity.class);
            startActivity(intent);
            finish();
        });
        speakTTBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, SpeakToSpeechActivity.class);
            startActivity(intent);
            finish();
        });
        todoBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, TodoActivity.class);
            startActivity(intent);
            finish();
        });


    }
}