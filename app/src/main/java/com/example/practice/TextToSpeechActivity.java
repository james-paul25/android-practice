package com.example.practice;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class TextToSpeechActivity extends AppCompatActivity {

    private EditText text;
    private Button ttsBtn;
    private TextToSpeech tts; //Declare the TextToSpeech

    public void speak(EditText txt){
        String text = txt.getText().toString();
        tts = new TextToSpeech(getApplicationContext(), status -> {
           if(status == TextToSpeech.SUCCESS){
               tts.setLanguage(Locale.US);
               tts.speak("Hello, jap. You said: " + text,  TextToSpeech.QUEUE_FLUSH, null, null);
           }
        });
    }

    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.text_to_speech_layout);

        text = findViewById(R.id.text);
        ttsBtn = findViewById(R.id.ttsBtn);

        ttsBtn.setOnClickListener(v -> {
            speak(text);
        });
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
