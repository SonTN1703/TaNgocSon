package com.example.bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int milliseconds = 0;
    private boolean running;
    private boolean wasRunning;
    private Button btStart;
    private Button btStop;
    private Button btReset;
    private TextView timeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        runTimer();
    }

    private void initView() {
        timeView = findViewById(R.id.time_view);
        btStart = findViewById(R.id.start_button);
        btReset = findViewById(R.id.reset_button);
        btStop = findViewById(R.id.stop_button);
    }

    private void initListener() {
        btReset.setOnClickListener(v -> {
            running = false;
            milliseconds = 0;
        });
        btStart.setOnClickListener(v -> {
            running = true;
        });
        btStop.setOnClickListener(v -> {
            running = false;
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    private void runTimer() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int millis = milliseconds % 100;
                int secs = (milliseconds % 6000) / 100;
                int minutes = (milliseconds % 360000) / 6000;
                int hours = milliseconds / 360000;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d:%02d", hours, minutes, secs, millis);
                timeView.setText(time);
                if (running) {
                    milliseconds++;
                }
                handler.postDelayed(this, 10);
            }
        });
    }
}