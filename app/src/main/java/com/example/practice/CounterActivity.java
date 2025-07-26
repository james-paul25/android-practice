package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class CounterActivity extends AppCompatActivity {

    private Button incrementBtn;
    private Button decrementBtn;
    private Button resetBtn;
    private Button backBtn;
    private TextView txt;

    public void increment(TextView txt){
        int counter = Integer.parseInt(txt.getText().toString());

        counter++;
        txt.setText(String.valueOf(counter));
    }
    public void decrement(TextView txt){
        int counter = Integer.parseInt(txt.getText().toString());

        counter--;
        txt.setText(String.valueOf(counter));
    }
    public void reset(TextView txt){
        int counter = 0;
        txt.setText(String.valueOf(counter));
    }

    @Override
    protected void onCreate(Bundle saveInstance){
        super.onCreate(saveInstance);
        EdgeToEdge.enable(this);
        setContentView(R.layout.counter_layout);

        incrementBtn = findViewById(R.id.incrementBtn);
        decrementBtn = findViewById(R.id.decrementBtn);
        resetBtn = findViewById(R.id.resetBtn);
        backBtn = findViewById(R.id.backBtn);
        txt = findViewById(R.id.counterTxt);

        incrementBtn.setOnClickListener(v -> {
            increment(txt);
        });
        decrementBtn.setOnClickListener(v -> {
            decrement(txt);
        });
        resetBtn.setOnClickListener(v -> {
            reset(txt);
        });
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }
}
