package com.example.stopwatchapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    int milliseconds = 0;
    boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.timerText);

        startTimer();

    }

    public void onClickStart(View view){
        isRunning = true;
    }
    public void onClickStop(View view){
        isRunning = false;
    }
    public void onClickReset(View view){
        isRunning = false;
        milliseconds = 0;
    }

    public void startTimer(){
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int ms = (int) (milliseconds % 1000);
                int totalSeconds = (int) (milliseconds / 1000);
                int s = totalSeconds % 60;
                int totalMinutes = totalSeconds / 60;
                int m = totalMinutes % 60;

                if (isRunning){
                    milliseconds += 10;
                }

                String formatString = String.format(Locale.getDefault(),"%02d:%02d:%03d", m, s, ms);

                textView.setText(formatString);
                handler.postDelayed(this,10);
            }
        };
        handler.post(runnable);
    }
}