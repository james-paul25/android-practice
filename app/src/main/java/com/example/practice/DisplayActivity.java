package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private Button backBtn;
    private EditText editText;

    public void displayName(TextView textView, EditText editText){
        String value = editText.getText().toString();
        textView.setText("Name: " + value);
    }

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.display_layout);

        textView = findViewById(R.id.displayName);
        button = findViewById(R.id.enter);
        editText = findViewById(R.id.editName);
        backBtn = findViewById(R.id.backBtn);

        button.setOnClickListener(v -> {
            displayName(textView, editText);
        });
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
